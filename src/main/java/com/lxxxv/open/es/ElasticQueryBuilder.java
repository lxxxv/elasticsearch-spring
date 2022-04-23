package com.lxxxv.open.es;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
public class ElasticQueryBuilder {
    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;
    private final ElasticQueryBuilderUtils builderUtils;
    private final int MAX_DIV_QUERY = 2;
    private final int MAX_SCROLL_SIZE = 100;
    private final int SCROLL_EXPIRE_MILLIS = (1000 * 60) * 5;  //5분

    public ElasticQueryBuilder(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
        this.builderUtils = new ElasticQueryBuilderUtils();
    }

    // indices = "index_fw_20220410-000001"
    // storedFields = "action", "category"
    // size = if size == -1 : unLimit
    public void matchAll(List<String> indices, List<String> storedFields, int size, Consumer<ElasticCallback> caller) {
        assert indices != null;
        assert storedFields != null;
        assert caller != null;

        SearchSourceBuilder builder = builderUtils.newSearchSourceBuilder();
        builderUtils.putTrackTotalHits(builder);
        builderUtils.putStoredFields(builder, storedFields);
        builderUtils.putScrollSize(builder, MAX_SCROLL_SIZE);
        builderUtils.putMatchAllQuery(builder);

        this.runResponse(builder, indices, size, caller);
    }

    // indices = "index_fw_20220410-000001"
    // storedFields = "action", "category"
    // query_strings = " (_id:hfATD4AB_OjUc5Yw3MWs OR _id:ivATD4AB_OjUc5Yw3MWs) AND (origin_id:33 )"
    // 쿼리 스트링의 List 의미
    //   서브쿼리 지원을 위해 1 -> 2 -> 3 형태로 실행되도록 만들었습니다.
    // size = if size == -1 : unlimit
    public void boolFilter(List<String> indices, List<String> storedFields, List<String> query_strings, int size, Consumer<ElasticCallback> caller) {
        assert indices != null;
        assert storedFields != null;
        assert query_strings != null;
        assert caller != null;

        int idx = 0;
        List<String> ids = new ArrayList<>();
        for (String query_string : query_strings) {
            int finalIdx = idx+1;
            this.boolFilter(indices, storedFields, query_string, size, ids, (Sender) ->
            {
                if (finalIdx == query_strings.size())
                {
                    //
                    // 마지막 결과니까 밖으로 넘긴다.
                    //
                    caller.accept(Sender);
                }
                else
                {
                    ids.addAll(Sender.getIds());
                }
            });
            idx = idx + 1;
        }
    }

    private void boolFilter(List<String> indices, List<String> storedFields, String query_string, int size, List<String> ids, Consumer<ElasticCallback> caller) {
        assert indices != null;
        assert storedFields != null;
        assert query_string != null;
        assert ids != null;
        assert caller != null;

        SearchSourceBuilder builder = builderUtils.newSearchSourceBuilder();
        builderUtils.putTrackTotalHits(builder);
        builderUtils.putStoredFields(builder, storedFields);
        builderUtils.putScrollSize(builder, MAX_SCROLL_SIZE);
        builderUtils.putFilter(builder, query_string, ids);

        this.runResponse(builder, indices, size, caller);
    }

    private void runResponse(SearchSourceBuilder builder, List<String> indices, int size, Consumer<ElasticCallback> caller)
    {
        final Scroll scroll = builderUtils.newScroll(SCROLL_EXPIRE_MILLIS);

        SearchRequest request = builderUtils.newSearchRequest();
        builderUtils.putIndices(request, indices);
        request.scroll(scroll);
        request.source(builder);

        SearchResponse response = this.getSearchResponse(request);
        assert response != null;
        String scrollId = response.getScrollId();
        SearchHit[] searchHits = response.getHits().getHits();

        int idxHits = 0;
        while (searchHits != null && searchHits.length > 0) {
            caller.accept(this.responseToObject(request.toString(), response));
            if (size > 0) {
                idxHits = idxHits + searchHits.length;
                if (idxHits >= size) {
                    break;
                }
            }
            SearchScrollRequest scrollRequest = builderUtils.newSearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            response = this.getScrollResponse(scrollRequest);
            assert response != null;
            scrollId = response.getScrollId();
            searchHits = response.getHits().getHits();
        }

        ClearScrollRequest clearScrollRequest = builderUtils.newClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = this.clearScroll(clearScrollRequest);
        assert clearScrollResponse != null;
        boolean succeeded = clearScrollResponse.isSucceeded();
    }

    private ElasticCallback responseToObject(String request, SearchResponse response) {
        if (null != response) {
            try {
                Map<String, Object> objectResponse = objectMapper.readValue(response.toString(), new TypeReference<Map<String, Object>>() {
                });

                ElasticResponseParser esParser = new ElasticResponseParser();
                ArrayList<Object> hits = esParser.exportHits(objectResponse);
                assert hits != null;
                ArrayList<Object> fields = new ArrayList<>();
                ArrayList<String> ids = new ArrayList<>();
                for (Object hit : hits) {
                    Map<String, Object> hitMap = esParser.typeCaseMap(hit);
                    assert hitMap != null;
                    fields.add(hitMap.get("fields"));
                    ids.add(hitMap.get("_id").toString());
                }

                return new ElasticCallback(request, objectResponse, ids, fields);
            } catch (Exception e) {
                log.error("responseToObject : ", e);
                return null;
            }
        } else {
            return null;
        }
    }

    private SearchResponse getSearchResponse(SearchRequest request) {
        try {
            return client.search(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("SearchResponse : ", e);
            return null;
        }
    }

    private SearchResponse getScrollResponse(SearchScrollRequest scrollRequest) {
        try {
            return client.scroll(scrollRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("SearchResponse : ", e);
            return null;
        }
    }

    private ClearScrollResponse clearScroll(ClearScrollRequest clearScrollRequest) {
        try {
            return client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("SearchResponse : ", e);
            return null;
        }
    }
}
