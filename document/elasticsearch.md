
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



example group
    index list (indices)
        https://www.elastic.co/guide/en/elasticsearch/reference/current/cat-indices.html#cat-indices-api-request
        GET /_cat/indices
    index detail info
        GET /.apm-agent-configuration/_mapping
