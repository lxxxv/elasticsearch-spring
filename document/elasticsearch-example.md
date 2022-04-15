```
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
        analysis _tag1
        ["analysis"]
        Index Shard Allocation : https://www.elastic.co/guide/en/elasticsearch/reference/current/index-modules-allocation.html
        ["index.routing.allocation.include.{attribute}", "index.routing.allocation.require.{attribute}", "index.routing.allocation.exclude.{attribute}", "index.unassigned.node_left.delayed_timeout", "index.routing.allocation.total_shards_per_node", "cluster.routing.allocation.total_shards_per_node", "index.routing.allocation.include._tier_preference"]
        Slowlog : https://www.elastic.co/guide/en/elasticsearch/reference/current/index-modules-slowlog.html
        ["index.search.slowlog.threshold.query.warn", "index.search.slowlog.threshold.query.info", "index.search.slowlog.threshold.query.debug", "index.search.slowlog.threshold.query.trace", "index.search.slowlog.threshold.fetch.warn", "index.search.slowlog.threshold.fetch.info", "index.search.slowlog.threshold.fetch.debug", "index.search.slowlog.threshold.fetch.trace"]


_tag1 settings.analysis : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis.html
["analyzer", "filter", "normalizer", "tokenizer"]


settings.analysis.analyzer : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-custom-analyzer.html
    built-in
    https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-analyzers.html
    ["standard", "simple", "whitespace", "stop", "keyword", "pattern", "language", "fingerprint"]
    language
    https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-lang-analyzer.html
    custom
        settings.analysis.analyzer.{my_custom_analyzer}
        ["type", "tokenizer", "char_filter", "filter"]
        settings.analysis.analyzer.{my_custom_analyzer}.type : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-analyzers.html
        ["standard", "simple", "whitespace", "stop", "keyword", "pattern", "english", "fingerprint", "custom"]
        settings.analysis.analyzer.{my_custom_analyzer}.tokenizer : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-tokenizers.html
        ["standard", "letter", "lowercase", "whitespace", "uax_url_email" "classic", "thai", "ngram", "edge_ngram", "keyword", "pattern", "simple_pattern", "char_group", "simple_pattern_split", "path_hierarchy"]
        settings.analysis.analyzer.{my_custom_analyzer}.char_filter : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-charfilters.html
        ["html_strip", "mapping", "pattern_replace"]
        settings.analysis.analyzer.{my_custom_analyzer}.filter : https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-tokenfilters.html
        ["apostrophe", "asciifolding", "cjk_bigram", "cjk_width", "classic", "common_grams", "condition", "decimal_digit", "delimited_payload", "dictionary_decompounder", "edge_ngram", "elision", "fingerprint", "synonym_graph", "hunspell", "hyphenation_decompounder", "keep_types", "keep", "stemmer", "keyword_repeat", "kstem", "length", "limit", "lowercase", "min_hash", "multiplexer", "ngram", "pattern_capture", "pattern_replace", "analysis-phonetic", "porter_stem", "predicate_token_filter", "keyword_repeat", "reverse", "shingle", "snowball", "stemmer", "stemmer_override", "stop", "synonym", "synonym_graph", "trim", "truncate", "unique", "uppercase", "word_delimiter", "word_delimiter_graph", "my_custom_filter_name"]

01.
PUT my-index-000001
{
    "settings":
    {
        "number_of_shards": 8,
        "number_of_replicas": 1,
        "analysis":
        {
            "analyzer":
            {
                "my_custom_analyzer":
                {
                    "type": "custom",
                    "tokenizer": "my_nori_tokenizer_mixed",
                    "char_filter": ["html_strip"],
                    "filter": ["lowercase"]
                }
            },
            "tokenizer":
            {
                "my_nori_tokenizer_mixed":
                {
                    "type": "nori_tokenizer",
                    "decompound_mode": "mixed"
                }
            },
            "filter": 
            {
                "engram_f": 
                {
                    "type": "edge_ngram",
                    "min_gram": 2,
                    "max_gram": 5
                }
            },
            "normalizer": 
            {
                "norm_low": 
                {
                    "type": "custom",
                    "filter": [ "lowercase", "asciifolding" ]
                }
            }
        }
    },
    "mappings":
    {
        "_doc":
        {
            "properties":
            {
                "body":
                {
                    "type":"text",
                    "analyzer": "my_custom_analyzer"
                },
                "body_keyword":
                {
                    "type":"keyword",
                    "normalizer": "norm_low"
                }
            }
        }
    }
}

02.
PUT lxxxv-index-01
{
  "settings":
  {
    "number_of_shards": 5,
    "number_of_replicas": 1,
    "analysis":
    {
      "analyzer":
      {
        "my_custom_analyzer":
        {
          "type": "custom",
          "char_filter": ["html_strip"],
          "tokenizer": "my_nori_tokenizer_mixed",
          "filter": ["lowercase"]
        }
      },
      "tokenizer":
      {
        "my_nori_tokenizer_mixed":
        {
          "type": "nori_tokenizer",
          "decompound_mode": "mixed"
        }
      },
      "normalizer":
      {
        "norm_low":
        {
          "type": "custom",
          "filter": [ "lowercase", "asciifolding" ]
        }
      }
    }
  },
  "mappings":
  {
    "properties":
    {
      "body":
      {
        "type":"text",
        "analyzer": "my_custom_analyzer",
        "fielddata": true
      },
      "body_keyword":
      {
        "type":"keyword",
        "normalizer": "norm_low"
      }
    }
  }
}



clone index
POST /my-index-000001/_clone/cloned-my-index-000001

```