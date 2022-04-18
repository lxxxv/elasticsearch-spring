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

public class ElasticQueryBuilderUtils
{
    private final int MAX_SCROLL_SIZE = 10;

    public void putFilter(SearchSourceBuilder builder, Map<String, String> filter)
    {
        assert builder != null;
        assert filter != null;

        BoolQueryBuilder boolQueryBuilder = null;
        for(String key: filter.keySet())
        {
            String field = key.trim();
            boolean isTerms = false;
            if (field.indexOf("{terms}.") == 0)
            {
                isTerms = true;
                field = field.replace("{terms}.", "");
            }
            if (null != boolQueryBuilder)
            {
                if (isTerms)
                {
                    boolQueryBuilder.filter(QueryBuilders.termsQuery(field, filter.get(key)));
                }
                else
                {
                    boolQueryBuilder.filter(QueryBuilders.termQuery(field, filter.get(key)));
                }
            }
            else
            {
                if (isTerms)
                {
                    boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.termsQuery(field, filter.get(key)));
                }
                else
                {
                    boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.termQuery(field, filter.get(key)));
                }
            }
        }
        builder.query(boolQueryBuilder);
    }

    public void putQuery(SearchSourceBuilder builder, Map<String, String> query)
    {
        assert builder != null;
        assert query != null;

        QueryStringQueryBuilder queryStringQueryBuilder = null;
        for (String key : query.keySet())
        {
            if (null != queryStringQueryBuilder)
            {
            }
            else
            {
                queryStringQueryBuilder = QueryBuilders.queryStringQuery(query.get(key));
            }
        }
        builder.query(queryStringQueryBuilder);
    }

    public void putIndices(SearchRequest request, List<String> indices)
    {
        if (null != request) if (null != indices && !indices.isEmpty()) request.indices(indices.toArray(new String[0]));
    }

    public void putTrackTotalHits(SearchSourceBuilder builder)
    {
        assert builder != null;
        builder.trackTotalHits(true);
    }

    public void putStoredFields(SearchSourceBuilder builder, List<String> storedFields)
    {
        assert builder != null;
        assert storedFields != null;
        if (!storedFields.isEmpty()) builder.storedFields(storedFields);
    }

    public void putMatchAllQuery(SearchSourceBuilder builder)
    {
        assert builder != null;
        builder.query(QueryBuilders.matchAllQuery());
    }

    public void putScrollSize(SearchSourceBuilder builder, int scrollSize)
    {
        assert builder != null;
        if (scrollSize > 0) builder.size(Math.min(scrollSize, MAX_SCROLL_SIZE));
    }

    public SearchSourceBuilder newSearchSourceBuilder()
    {
        return new SearchSourceBuilder();
    }

    public SearchRequest newSearchRequest()
    {
        return new SearchRequest();
    }

    public Scroll newScroll()
    {
        return new Scroll(TimeValue.timeValueMinutes(1L));
    }

    public SearchScrollRequest newSearchScrollRequest(String scrollId)
    {
        return new SearchScrollRequest(scrollId);
    }

    public ClearScrollRequest newClearScrollRequest()
    {
        return new ClearScrollRequest();
    }
}
