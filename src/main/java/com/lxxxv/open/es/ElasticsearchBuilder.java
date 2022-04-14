package com.lxxxv.open.es;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.*;
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

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Slf4j
public class ElasticsearchBuilder
{
    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;
    private final Map<String, Object> param = new ConcurrentHashMap<>();

    public ElasticsearchBuilder(RestHighLevelClient _client, ObjectMapper _objectMapper)
    {
        this.client = _client;
        this.objectMapper = _objectMapper;
    }

    public void searchQuery(List<String> indices, List<String> storedFields, Map<String, String> query, Consumer<Map<String, Object>> caller)
    {
        SearchSourceBuilder builder = this.getSearchSourceBuilder();
        this.putTrackTotalHits(builder);
        this.putStoredFields(builder, storedFields);
        this.putQuery(builder, query);

        SearchRequest request = this.getSearchRequest();
        this.putIndices(request, indices);
        request.source(builder);

        SearchResponse response = this.getSearchResponse(request);
        caller.accept(this.responseToObject(response));
    }

    public void searchFilter(List<String> indices, List<String> storedFields, Map<String, String> filter, Consumer<Map<String, Object>> caller)
    {
        SearchSourceBuilder builder = this.getSearchSourceBuilder();
        this.putTrackTotalHits(builder);
        this.putStoredFields(builder, storedFields);
        this.putFilter(builder, filter);

        SearchRequest request = this.getSearchRequest();
        this.putIndices(request, indices);
        request.source(builder);

        SearchResponse response = this.getSearchResponse(request);
        caller.accept(this.responseToObject(response));
    }

    public final void aggregation(List<String> indices, List<String> storedFields, Map<String, String> query, String _field, Consumer<Map<String, Object>> caller)
    {
        try
        {
            SearchSourceBuilder builder = this.getSearchSourceBuilder();
            this.putTrackTotalHits(builder);
            this.putStoredFields(builder, storedFields);
            this.putQuery(builder, query);

            TermsAggregationBuilder aggregation = AggregationBuilders.terms("by-esBuilder").field(_field);
            aggregation.size(Integer.MAX_VALUE);
            builder.aggregation(aggregation);

            SearchRequest request = this.getSearchRequest();
            this.putIndices(request, indices);
            request.source(builder);

            SearchResponse response = this.client.search(request, RequestOptions.DEFAULT);
            caller.accept(this.responseToObject(response));
        }
        catch(Exception e)
        {
            log.error("An error occurred while searching elasticsearch, ", e);
        }
    }

    private Map<String, Object> responseToObject(SearchResponse response)
    {
        if (null != response)
        {
            try
            {
                return objectMapper.readValue(response.toString(), new TypeReference<Map<String, Object>>() {});
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

    private void putIndices(SearchRequest request, List<String> indices)
    {
        if (null != indices && !indices.isEmpty()) request.indices(indices.toArray(new String[0]));
    }

    private void putTrackTotalHits(SearchSourceBuilder builder)
    {
        builder.trackTotalHits(true);
    }

    private void putStoredFields(SearchSourceBuilder builder, List<String> storedFields)
    {
        if (null != storedFields && !storedFields.isEmpty()) builder.storedFields(storedFields);
    }

    private void putFilter(SearchSourceBuilder builder, Map<String, String> filter)
    {
        if (null != filter)
        {
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
                        boolQueryBuilder.filter(QueryBuilders.termsQuery(key, filter.get(key)));
                    }
                    else
                    {
                        boolQueryBuilder.filter(QueryBuilders.termQuery(key, filter.get(key)));
                    }
                }
                else
                {
                    if (isTerms)
                    {
                        boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.termsQuery(key, filter.get(key)));
                    }
                    else
                    {
                        boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.termQuery(key, filter.get(key)));
                    }
                }
            }
            builder.query(boolQueryBuilder);
        }
    }

    private void putQuery(SearchSourceBuilder builder, Map<String, String> query)
    {
        if (null != query)
        {
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
    }

    private SearchSourceBuilder getSearchSourceBuilder()
    {
        return new SearchSourceBuilder();
    }

    private SearchRequest getSearchRequest()
    {
        return new SearchRequest();
    }
}
