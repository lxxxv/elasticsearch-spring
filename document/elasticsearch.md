
Configuring
https://www.elastic.co/guide/en/elasticsearch/reference/current/settings.html


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
        GET _cat/aliases?v=true&s=index
    cat allocation
    cat anomaly detectors
    cat count
        GET /_cat/count?v=true&s=count
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
        GET /_cat/segments?v=true&s=index
    cat shards
        GET /_cat/shards?v=true&s=index
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
        GET /_cat/indices/_all?v=true&s=index
    index detail info
        GET /.apm-agent-configuration/_mapping

