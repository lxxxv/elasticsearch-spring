package com.lxxxv.open.es;

import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.List;
import java.util.Map;

public class ElasticQueryBuilderUtils {
    public void putFilter(SearchSourceBuilder builder, String query_string, List<String> ids) {
        assert builder != null;
        assert query_string != null;

        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(" ( " + query_string + " ) ");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().filter(queryStringQueryBuilder);

        if (ids.size() > 0)
        {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("_id", ids));
        }

//        for(String id : ids)
//        {
//            boolQueryBuilder.filter(QueryBuilders.termsQuery("_id", id));
//        }

        builder.query(boolQueryBuilder);
    }

    public void putQuery(SearchSourceBuilder builder, Map<String, String> query) {
        assert builder != null;
        assert query != null;

        QueryStringQueryBuilder queryStringQueryBuilder = null;
        for (String key : query.keySet()) {
            if (null != queryStringQueryBuilder) {
            } else {
                queryStringQueryBuilder = QueryBuilders.queryStringQuery(query.get(key));
            }
        }
        builder.query(queryStringQueryBuilder);
    }

    public void putIndices(SearchRequest request, List<String> indices) {
        if (null != request) if (null != indices && !indices.isEmpty()) request.indices(indices.toArray(new String[0]));
    }

    public void putTrackTotalHits(SearchSourceBuilder builder) {
        assert builder != null;
        builder.trackTotalHits(true);
    }

    public void putStoredFields(SearchSourceBuilder builder, List<String> storedFields) {
        assert builder != null;
        assert storedFields != null;
        if (!storedFields.isEmpty()) builder.storedFields(storedFields);
    }

    public void putMatchAllQuery(SearchSourceBuilder builder) {
        assert builder != null;
        builder.query(QueryBuilders.matchAllQuery());
    }

    public void putScrollSize(SearchSourceBuilder builder, int scrollSize) {
        assert builder != null;
        builder.size(scrollSize);
        //if (scrollSize > 0) builder.size(Math.min(scrollSize, MAX_SCROLL_SIZE));
    }

    public SearchSourceBuilder newSearchSourceBuilder() {
        return new SearchSourceBuilder();
    }

    public SearchRequest newSearchRequest() {
        return new SearchRequest();
    }

    public Scroll newScroll(int millis) {
        return new Scroll(TimeValue.timeValueMillis(millis));
    }

    public SearchScrollRequest newSearchScrollRequest(String scrollId) {
        return new SearchScrollRequest(scrollId);
    }

    public ClearScrollRequest newClearScrollRequest() {
        return new ClearScrollRequest();
    }
}
