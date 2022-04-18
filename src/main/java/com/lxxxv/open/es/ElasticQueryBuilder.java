package com.lxxxv.open.es;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.elasticsearch.action.search.*;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.AggregatorFactories;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetMappingsResponse;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.cluster.metadata.MappingMetadata;

import com.lxxxv.open.es.ElasticResponseParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
public class ElasticQueryBuilder
{
    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;
    private final ElasticQueryBuilderUtils builderUtils;

    public ElasticQueryBuilder(RestHighLevelClient client, ObjectMapper objectMapper)
    {
        this.client = client;
        this.objectMapper = objectMapper;
        this.builderUtils = new ElasticQueryBuilderUtils();
    }

    public void matchAll(List<String> indices, List<String> storedFields, int size, Consumer<ElasticCallback> caller)
    {
        final Scroll scroll = builderUtils.newScroll();

        SearchSourceBuilder builder = builderUtils.newSearchSourceBuilder();
        builderUtils.putTrackTotalHits(builder);
        builderUtils.putStoredFields(builder, storedFields);
        builderUtils.putScrollSize(builder, size);
        builderUtils.putMatchAllQuery(builder);

        SearchRequest request = builderUtils.newSearchRequest();
        builderUtils.putIndices(request, indices);
        request.scroll(scroll);
        request.source(builder);

        SearchResponse response = this.getSearchResponse(request);
        assert response != null;
        String scrollId = response.getScrollId();
        SearchHit[] searchHits = response.getHits().getHits();

        int idx = 0;
        while(searchHits != null && searchHits.length > 0)
        {
            caller.accept(this.responseToObject(request.toString(), response));
            idx = idx + searchHits.length;
            if (idx >= size)
            {
                break;
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

    private ElasticCallback responseToObject(String request, SearchResponse response)
    {
        if (null != response)
        {
            try
            {
                Map<String, Object> objectResponse = objectMapper.readValue(response.toString(), new TypeReference<Map<String, Object>>() {});

                ElasticResponseParser esParser = new ElasticResponseParser();
                ArrayList<Object> hits = esParser.exportHits(objectResponse);
                ArrayList<Object> fields = esParser.exportFields(hits);

                return new ElasticCallback(request, objectResponse, fields);
            }
            catch(Exception e)
            {
                log.error("responseToObject : ", e);
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    private SearchResponse getSearchResponse(SearchRequest request)
    {
        try
        {
            return client.search(request, RequestOptions.DEFAULT);
        } catch (IOException e)
        {
            log.error("SearchResponse : ", e);
            return null;
        }
    }

    private SearchResponse getScrollResponse(SearchScrollRequest scrollRequest)
    {
        try
        {
            return client.scroll(scrollRequest, RequestOptions.DEFAULT);
        } catch (IOException e)
        {
            log.error("SearchResponse : ", e);
            return null;
        }
    }

    private ClearScrollResponse clearScroll(ClearScrollRequest clearScrollRequest)
    {
        try
        {
            return client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        } catch (IOException e)
        {
            log.error("SearchResponse : ", e);
            return null;
        }
    }
}
