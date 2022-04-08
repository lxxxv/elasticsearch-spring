
create index : https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-create-index.html
["aliases", "mappings", "settings"]

aliases
[]
mappings
[]
settings : https://www.elastic.co/guide/en/elasticsearch/reference/current/index-modules.html
    static
    [index.number_of_shards, "index.number_of_routing_shards", "index.codec", "index.routing_partition_size", "index.soft_deletes.enabled", "index.soft_deletes.retention_lease.period", "index.load_fixed_bitset_filters_eagerly", "index.shard.check_on_startup"]
    dynamic
    ["index.number_of_replicas", "index.auto_expand_replicas", "index.search.idle.after", "index.refresh_interval", "index.max_result_window", "index.max_inner_result_window", "index.max_rescore_window", "index.max_docvalue_fields_search", "index.max_script_fields", "index.max_ngram_diff", "index.max_shingle_diff", "index.max_refresh_listeners", "index.analyze.max_token_count", "index.highlight.max_analyzed_offset", "index.max_terms_count", "index.max_regex_length", "index.query.default_field", "index.routing.allocation.enable", "index.routing.rebalance.enable", "index.gc_deletes", "index.default_pipeline", "index.final_pipeline", "index.hidden"]
    other
        analysis
        ["analysis"]
        Index Shard Allocation : https://www.elastic.co/guide/en/elasticsearch/reference/current/index-modules-allocation.html
        ["index.routing.allocation.include.{attribute}", "index.routing.allocation.require.{attribute}", "index.routing.allocation.exclude.{attribute}", "index.unassigned.node_left.delayed_timeout", "index.routing.allocation.total_shards_per_node", "cluster.routing.allocation.total_shards_per_node", "index.routing.allocation.include._tier_preference"]
        Slowlog : https://www.elastic.co/guide/en/elasticsearch/reference/current/index-modules-slowlog.html
        ["index.search.slowlog.threshold.query.warn", "index.search.slowlog.threshold.query.info", "index.search.slowlog.threshold.query.debug", "index.search.slowlog.threshold.query.trace", "index.search.slowlog.threshold.fetch.warn", "index.search.slowlog.threshold.fetch.info", "index.search.slowlog.threshold.fetch.debug", "index.search.slowlog.threshold.fetch.trace"]


settings.analysis : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis.html
["analyzer", "filter"]
settings.analysis.analyzer : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-custom-analyzer.html
settings.analysis.filter : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-stop-tokenfilter.html


settings.analysis.analyzer.{my_custom_analyzer}
["type", "tokenizer", "char_filter", "filter"]
settings.analysis.analyzer.{my_custom_analyzer}.type : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-analyzers.html
["standard", "simple", "whitespace", "stop", "keyword", "pattern", "english", "fingerprint", "custom"]
settings.analysis.analyzer.{my_custom_analyzer}.tokenizer : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-tokenizers.html
["standard", "letter", "lowercase", "whitespace", "uax_url_email" "classic", "thai", "ngram", "edge_ngram", "keyword", "pattern", "simple_pattern", "char_group", "simple_pattern_split", "path_hierarchy"]
settings.analysis.analyzer.{my_custom_analyzer}.char_filter : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-charfilters.html
["html_strip", "mapping", "pattern_replace"]
settings.analysis.analyzer.{my_custom_analyzer}.filter : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-stop-tokenfilter.html
["lowercase", "stop", "my_custom_filter_name"]

PUT my-index-000001
{
    "settings":
    {
        "analysis":
        {
            "analyzer":
            {
                "my_custom_analyzer":
                {
                    "type": "custom",
                    "tokenizer": "standard",
                    "char_filter": ["html_strip"],
                    "filter": ["lowercase","asciifolding"]
                }
            }
        }
    }
    ,
    "mappings":
    {
    }
}



clone index
POST /my-index-000001/_clone/cloned-my-index-000001