```
Configuring
https://www.elastic.co/guide/en/elasticsearch/reference/current/settings.html


Index APIs
https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html
    Index management
        Create index
            https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-create-index.html
            https://www.elastic.co/guide/en/elasticsearch/reference/current/index-modules.html#index-modules-settings
                https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis.html
            https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping.html
                https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-types.html
                https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-params.html
            https://www.elastic.co/guide/en/elasticsearch/reference/current/aliases.html
        Delete index
        Get index
        Exists
        Close index
        Open index
        Shrink index
        Split index
        Clone index
        Rollover
        Unfreeze index
        Resolve index
    Mapping management
        Update mapping
        Get mapping
        Get field mapping
        Analyze index disk usage
    Alias management
        Aliases
        Create or update alias
        Get alias
        Alias exists
        Delete alias
    Index settings
        Update index settings
        Get index settings
        Analyze
    Index templates
        Create or update index template
        Get index template
        Delete index template
        Create or update component template
        Get component template
        Delete component template
        Simulate index
        Simulate template
    Monitoring
        Index stats
        Field usage stats
        Index segments
        Index recovery
        Index shard stores
    Status management
        Clear cache
        Refresh
        Flush
        Force merge
    Dangling indices
        List dangling indices
        Import dangling index
        Delete dangling index


Mapping
https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping.html
    Dynamic mapping
    Explicit mapping
    Runtime fields
    Field data types
    Metadata fields
    Mapping parameters
    Mapping limit settings
    Removal of mapping types


Text analysis
https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis.html
    Built-in analyzer reference
        https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-analyzers.html
        Standard Analyzer
        Simple Analyzer
        Whitespace Analyzer
        Stop Analyzer
        Keyword Analyzer
        Pattern Analyzer
        Language Analyzers
        Fingerprint Analyzer
    Create a custom analyzer
        https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-custom-analyzer.html
        type
        tokenizer
        char_filter
        filter
        position_increment_gap
    char filter (0~3)
        https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-charfilters.html
    tokenizer (1)
        https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-tokenizers.html
        char_group
        classic
        edge_ngram
        keyword
        letter
        lowercase
        ngram
        path_hierarchy
        pattern
        simple_pattern
        simple_pattern_split
        standard
        thai
        uax_url_email
        whitespace
    token filter (0~n)
        https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-tokenfilters.html
        apostrophe
        ascii_folding
        cjk_bigram
        cjk_width
        classic
        common_grams
        conditional
        decimal_digit
        delimited_payload
        dictionary_decompounder
        edge_ngram
        elision
        fingerprint
        flatten_graph
        hunspell
        hyphenation_decompounder
        keep_types
        keep_words
        keyword_marker
        keyword_repeat
        kstem
        length
        limit
        lowercase
        minhash
        multiplexer
        ngram
        normalization
        pattern_capture
        pattern_replace
        phonetic
        porter_stem
        predicate_token_filter
        remove_duplicates
        reverse
        shingle
        snowball
        stemmer
        stemmer_override
        stop
        synonym
        synonym_graph
        trim
        truncate
        unique
        uppercase
        word_delimiter
        word_delimiter_graph


Query DSL
https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl.html
    Query and filter context
    Compound queries
    Full text queries
        Intervals
        Match
        Match boolean prefix
        Match phrase
        Match phrase prefix
        Combined fields
        Multi-match
        Query string
        Simple query string
    Geo queries
    Shape queries
    Joining queries
    Match all
    Span queries
    Specialized queries
    Term-level queries
    minimum_should_match parameter
    rewrite parameter
    Regular expression syntax


Compact and aligned text (CAT) APIs
https://www.elastic.co/guide/en/elasticsearch/reference/current/cat.html
    cat aliases
        GET _cat/aliases?v=true&s=index&h=alias,index,filter,routing.index,routing.search,is_write_index
    cat allocation
    cat anomaly detectors
    cat count
        GET /_cat/count?v=true&s=count&h=epoch,timestamp,count
    cat data frame analytics
    cat datafeeds
    cat fielddata
    cat health
    cat indices
        GET /_cat/indices/_all?v=true&s=index
    cat master
        GET /_cat/master?v=true&s=node
    cat nodeattrs
    cat nodes
        GET /_cat/nodes?v=true&full_id=true
    cat pending tasks
        GET /_cat/pending_tasks
    cat plugins
        GET /_cat/plugins?v=true&s=component
    cat recovery
    cat repositories
        GET /_cat/repositories?v=true
    cat segments
        GET /_cat/segments?v=true&s=index&h=index,shard,prirep,ip,id,segment,generation,docs.count,docs.deleted,size,size.memory,committed,searchable,version,compound
    cat shards
        GET /_cat/shards?v=true&s=index&h=index,shard,prirep,state,docs,store,ip,id,node,sync_id,unassigned.reason,unassigned.at,unassigned.for,unassigned.details,recoverysource.type,completion.size,fielddata.memory_size,fielddata.evictions,query_cache.memory_size,query_cache.evictions,flush.total,flush.total_time,get.current,get.time,get.total,get.exists_time,get.exists_total,get.missing_time,get.missing_total,indexing.delete_current,indexing.delete_time,indexing.delete_total,indexing.index_current,indexing.index_time,indexing.index_total,indexing.index_failed,merges.current,merges.current_docs,merges.current_size,merges.total,merges.total_docs,merges.total_size,merges.total_time,refresh.total,refresh.time,refresh.external_total,refresh.external_time,refresh.listeners,search.fetch_current,search.fetch_time,search.fetch_total,search.open_contexts,search.query_current,search.query_time,search.query_total,search.scroll_current,search.scroll_time,search.scroll_total,segments.count,segments.memory,segments.index_writer_memory,segments.version_map_memory,segments.fixed_bitset_memory,seq_no.max,seq_no.local_checkpoint,seq_no.global_checkpoint,warmer.current,warmer.total,warmer.total_time,path.data,path.state
    cat snapshots
    cat task management
        GET _cat/tasks?v=true
    cat templates
        GET /_cat/templates?v=true&s=name
    cat thread pool
        GET /_cat/thread_pool?v=true&s=name
    cat trained model
    cat transforms
        GET /_cat/transforms/_all?v=true


example group
    index list (indices)
        https://www.elastic.co/guide/en/elasticsearch/reference/current/cat-indices.html#cat-indices-api-request
        GET /_cat/indices/_all?v=true&s=index&h=health,status,index,uuid,pri,rep,docs.count,docs.deleted,creation.date,creation.date.string,store.size,pri.store.size,completion.size,pri.completion.size,fielddata.memory_size,pri.fielddata.memory_size,fielddata.evictions,pri.fielddata.evictions,query_cache.memory_size,pri.query_cache.memory_size,query_cache.evictions,pri.query_cache.evictions,request_cache.memory_size,pri.request_cache.memory_size,request_cache.evictions,pri.request_cache.evictions,request_cache.hit_count,pri.request_cache.hit_count,request_cache.miss_count,pri.request_cache.miss_count,flush.total,pri.flush.total,flush.total_time,pri.flush.total_time,get.current,pri.get.current,get.time,pri.get.time,get.total,pri.get.total,get.exists_time,pri.get.exists_time,get.exists_total,pri.get.exists_total,get.missing_time,pri.get.missing_time,get.missing_total,pri.get.missing_total,indexing.delete_current,pri.indexing.delete_current,indexing.delete_time,pri.indexing.delete_time,indexing.delete_total,pri.indexing.delete_total,indexing.index_current,pri.indexing.index_current,indexing.index_time,pri.indexing.index_time,indexing.index_total,pri.indexing.index_total,indexing.index_failed,pri.indexing.index_failed,merges.current,pri.merges.current,merges.current_docs,pri.merges.current_docs,merges.current_size,pri.merges.current_size,merges.total,pri.merges.total,merges.total_docs,pri.merges.total_docs,merges.total_size,pri.merges.total_size,merges.total_time,pri.merges.total_time,refresh.total,pri.refresh.total,refresh.time,pri.refresh.time,refresh.external_total,pri.refresh.external_total,refresh.external_time,pri.refresh.external_time,refresh.listeners,pri.refresh.listeners,search.fetch_current,pri.search.fetch_current,search.fetch_time,pri.search.fetch_time,search.fetch_total,pri.search.fetch_total,search.open_contexts,pri.search.open_contexts,search.query_current,pri.search.query_current,search.query_time,pri.search.query_time,search.query_total,pri.search.query_total,search.scroll_current,pri.search.scroll_current,search.scroll_time,pri.search.scroll_time,search.scroll_total,pri.search.scroll_total,segments.count,pri.segments.count,segments.memory,pri.segments.memory,segments.index_writer_memory,pri.segments.index_writer_memory,segments.version_map_memory,pri.segments.version_map_memory,segments.fixed_bitset_memory,pri.segments.fixed_bitset_memory,warmer.current,pri.warmer.current,warmer.total,pri.warmer.total,warmer.total_time,pri.warmer.total_time,suggest.current,pri.suggest.current,suggest.time,pri.suggest.time,suggest.total,pri.suggest.total,memory.total,pri.memory.total,search.throttled
    index detail info mappings
        GET /<target>/_mapping
    index detail info settings
        GET /<target>/_settings
    Delete index API
        DELETE /my-index-000001


```