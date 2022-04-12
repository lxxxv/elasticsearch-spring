```

REST APIs
|-- Data stream APIs
|   |-- Create data stream
|   |   `-- PUT /_data_stream/<data-stream>
|   |-- Delete data stream
|   |   `-- DELETE /_data_stream/<data-stream>
|   |-- Get data stream
|   |   `-- GET /_data_stream/<data-stream>
|   |-- Migrate to data stream
|   |   `-- POST /_data_stream/_migrate/<alias>
|   |-- Data stream stats
|   |   `-- GET /_data_stream/<data-stream>/_stats
|   |-- Promote data stream
|   |   `-- POST /_data_stream/_promote/<data-stream>
|   `-- Modify data streams
|       `-- POST /_data_stream/_modify
|-- Document APIs
|   |-- Reading and Writing documents
|   |-- Index
|   |   |-- PUT /<target>/_doc/<_id>
|   |   |-- POST /<target>/_doc/
|   |   |-- PUT /<target>/_create/<_id>
|   |   `-- POST /<target>/_create/<_id>
|   |-- Get
|   |   |-- GET <index>/_doc/<_id>
|   |   |-- HEAD <index>/_doc/<_id>
|   |   |-- GET <index>/_source/<_id>
|   |   `-- HEAD <index>/_source/<_id>
|   |-- Delete
|   |   `-- DELETE /<index>/_doc/<_id>
|   |-- Delete by query
|   |   `-- POST /<target>/_delete_by_query
|   |-- Update
|   |   `-- POST /<index>/_update/<_id>
|   |-- Update by query
|   |   `-- POST /<target>/_update_by_query
|   |-- Multi get
|   |   |-- GET /_mget
|   |   `-- GET /<index>/_mget
|   |-- Bulk
|   |   |-- POST /_bulk
|   |   `-- POST /<target>/_bulk
|   |-- Reindex
|   |   `-- POST /_reindex
|   |-- Term vectors
|   |   `-- GET /<index>/_termvectors/<_id>
|   |-- Multi term vectors
|   |   |-- POST /_mtermvectors
|   |   `-- POST /<index>/_mtermvectors
|   |-- ?refresh
|   |   `-- PUT /<index>/<type>/<_id>?refresh=true
|   `-- Optimistic concurrency control
|-- Cluster APIs
|   |-- Cluster allocation explain
|   |   |-- GET _cluster/allocation/explain
|   |   `-- POST _cluster/allocation/explain
|   |-- Cluster get settings
|   |   `-- GET /_cluster/settings
|   |-- Cluster health
|   |   `-- GET /_cluster/health/<target>
|   |-- Cluster reroute
|   |   `-- POST /_cluster/reroute
|   |-- Cluster state
|   |   `-- GET /_cluster/state/<metrics>/<target>
|   |-- Cluster stats
|   |   |-- GET /_cluster/stats
|   |   `-- GET /_cluster/stats/nodes/<node_filter>
|   |-- Cluster update settings
|   |   `-- PUT /_cluster/settings
|   |-- Nodes feature usage
|   |   |-- GET /_nodes/usage
|   |   |-- GET /_nodes/<node_id>/usage
|   |   |-- GET /_nodes/usage/<metric>
|   |   `-- GET /_nodes/<node_id>/usage/<metric>
|   |-- Nodes hot threads
|   |   |-- GET /_nodes/hot_threads
|   |   `-- GET /_nodes/<node_id>/hot_threads
|   |-- Nodes info
|   |   |-- GET /_nodes
|   |   |-- GET /_nodes/<node_id>
|   |   |-- GET /_nodes/<metric>
|   |   `-- GET /_nodes/<node_id>/<metric>
|   |-- Nodes reload secure settings
|   |   |-- POST /_nodes/reload_secure_settings
|   |   `-- POST /_nodes/<node_id>/reload_secure_settings
|   |-- Nodes stats
|   |   |-- GET /_nodes/stats
|   |   |-- GET /_nodes/<node_id>/stats
|   |   |-- GET /_nodes/stats/<metric>
|   |   |-- GET /_nodes/<node_id>/stats/<metric>
|   |   |-- GET /_nodes/stats/<metric>/<index_metric>
|   |   `-- GET /_nodes/<node_id>/stats/<metric>/<index_metric>
|   |-- Pending cluster tasks
|   |   `-- GET /_cluster/pending_tasks
|   |-- Remote cluster info
|   |   `-- GET /_remote/info
|   |-- Task management
|   |   |-- GET /_tasks/<task_id>
|   |   `-- GET /_tasks
|   |-- Voting configuration exclusions
|   |   |-- POST /_cluster/voting_config_exclusions?node_names=<node_names>
|   |   |-- POST /_cluster/voting_config_exclusions?node_ids=<node_ids>
|   |   `-- DELETE /_cluster/voting_config_exclusions
|   |-- Create or update desired nodes
|   |   `-- PUT /_internal/desired_nodes/<history_id>/<version>
|   |-- Get desired nodes
|   |   `-- GET /_internal/desired_nodes/_latest
|   `-- Delete desired nodes
|       `-- DELETE /_internal/desired_nodes
|-- Compact and aligned text (CAT) APIs
|   |-- cat aliases
|   |   `-- GET _cat/aliases?v=true&s=index&h=alias,index,filter,routing.index,routing.search,is_write_index
|   |-- cat allocation
|   |-- cat anomaly detectors
|   |-- cat count
|   |   `-- GET /_cat/count?v=true&s=count&h=epoch,timestamp,count
|   |-- cat data frame analytics
|   |-- cat datafeeds
|   |-- cat fielddata
|   |-- cat health
|   |-- cat indices
|   |   `-- GET /_cat/indices/_all?v=true&s=index
|   |-- cat master
|   |   `-- GET /_cat/master?v=true&s=node
|   |-- cat nodeattrs
|   |-- cat nodes
|   |   `-- GET /_cat/nodes?v=true&full_id=true
|   |-- cat pending tasks
|   |   `-- GET /_cat/pending_tasks
|   |-- cat plugins
|   |   `-- GET /_cat/plugins?v=true&s=component
|   |-- cat recovery
|   |   `-- GET /_cat/repositories?v=true
|   |-- cat repositories
|   |-- cat segments
|   |   `-- GET /_cat/segments?v=true&s=index&h=index,shard,prirep,ip,id,segment,generation,docs.count,docs.deleted,size,size.memory,committed,searchable,version,compound
|   |-- cat shards
|   |   `-- GET /_cat/shards?v=true&s=index&h=index,shard,prirep,state,docs,store,ip,id,node,sync_id,unassigned.reason,unassigned.at,unassigned.for,unassigned.details,recoverysource.type,completion.size,fielddata.memory_size,fielddata.evictions,query_cache.memory_size,query_cache.evictions,flush.total,flush.total_time,get.current,get.time,get.total,get.exists_time,get.exists_total,get.missing_time,get.missing_total,indexing.delete_current,indexing.delete_time,indexing.delete_total,indexing.index_current,indexing.index_time,indexing.index_total,indexing.index_failed,merges.current,merges.current_docs,merges.current_size,merges.total,merges.total_docs,merges.total_size,merges.total_time,refresh.total,refresh.time,refresh.external_total,refresh.external_time,refresh.listeners,search.fetch_current,search.fetch_time,search.fetch_total,search.open_contexts,search.query_current,search.query_time,search.query_total,search.scroll_current,search.scroll_time,search.scroll_total,segments.count,segments.memory,segments.index_writer_memory,segments.version_map_memory,segments.fixed_bitset_memory,seq_no.max,seq_no.local_checkpoint,seq_no.global_checkpoint,warmer.current,warmer.total,warmer.total_time,path.data,path.state
|   |-- cat snapshots
|   |-- cat task management
|   |   `-- GET _cat/tasks?v=true
|   |-- cat templates
|   |   `-- GET /_cat/templates?v=true&s=name
|   |-- cat thread pool
|   |   `-- GET /_cat/thread_pool?v=true&s=name
|   |-- cat trained model
|   `-- cat transforms
|       `-- GET /_cat/transforms/_all?v=true
`-- Index APIs
    |-- Index management
    |   |-- Create index
    |   |   `-- PUT /<index>
    |   |-- Delete index
    |   |   `-- DELETE /<index>
    |   |-- Get index
    |   |   `-- GET /<target>
    |   |-- Exists
    |   |   `-- HEAD <target>
    |   |-- Close index
    |   |   `-- POST /<index>/_close
    |   |-- Open index 
    |   |   `-- POST /<target>/_open
    |   |-- Shrink index
    |   |   |-- POST /<index>/_shrink/<target-index>
    |   |   `-- PUT /<index>/_shrink/<target-index>
    |   |-- Split index
    |   |   |-- POST /<index>/_split/<target-index>
    |   |   `-- PUT /<index>/_split/<target-index>   
    |   |-- Clone index
    |   |   |-- POST /<index>/_clone/<target-index>
    |   |   `-- PUT /<index>/_clone/<target-index>
    |   |-- Rollover
    |   |   |-- POST /<rollover-target>/_rollover/
    |   |   `-- POST /<rollover-target>/_rollover/<target-index>
    |   |-- Unfreeze index
    |   |   `-- POST /<index>/_unfreeze
    |   `-- Resolve index
    |       `-- GET /_resolve/index/<name>
    |-- Mapping management
    |   |-- Update mapping
    |   |   `-- PUT /<target>/_mapping
    |   |-- Get mapping
    |   |   |-- GET /_mapping
    |   |   `-- GET /<target>/_mapping
    |   |-- Get field mapping
    |   |   |-- GET /_mapping/field/<field>
    |   |   `-- GET /<target>/_mapping/field/<field>
    |   `-- Analyze index disk usage
    |       `-- POST /<target>/_disk_usage
    |-- Alias management    
    |   |-- Aliases
    |   |   `-- POST _aliases
    |   |-- Create or update alias
    |   |   |-- POST <target>/_alias/<alias>
    |   |   |-- POST <target>/_aliases/<alias>
    |   |   |-- PUT <target>/_alias/<alias>
    |   |   `-- PUT <target>/_aliases/<alias>
    |   |-- Get alias
    |   |   |-- GET _alias/<alias>
    |   |   |-- GET _alias
    |   |   `-- GET <target>/_alias/<alias>
    |   |-- Alias exists
    |   |   |-- HEAD _alias/<alias>
    |   |   `-- HEAD <target>/_alias/<alias>   
    |   `-- Delete alias
    |       |-- DELETE <target>/_alias/<alias>
    |       `-- DELETE <target>/_aliases/<alias>
    |-- Index settings
    |   |-- Update index settings
    |   |   `-- PUT /<target>/_settings
    |   |-- Get index settings
    |   |   |-- GET /<target>/_settings
    |   |   `-- GET /<target>/_settings/<setting>
    |   `-- Analyze
    |       |-- GET /_analyze
    |       |-- POST /_analyze
    |       |-- GET /<index>/_analyze
    |       `-- POST /<index>/_analyze
    |-- Index templates
    |   |-- Create or update index template
    |   |   `-- PUT /_index_template/<index-template>
    |   |-- Get index template
    |   |   `-- GET /_index_template/<index-template>
    |   |-- Delete index template
    |   |   `-- DELETE /_index_template/<index-template>
    |   |-- Create or update component template
    |   |   `-- PUT /_component_template/<component-template>
    |   |-- Get component template
    |   |   `-- GET /_component_template/<component-template>
    |   |-- Delete component template
    |   |   `-- DELETE /_component_template/<component-template>
    |   |-- Simulate index
    |   |   `-- POST /_index_template/_simulate_index/<index>
    |   `-- Simulate template
    |       `-- POST /_index_template/_simulate/<index-template>
    |-- Monitoring
    |   |-- Index stats
    |   |   |-- GET /<target>/_stats/<index-metric>
    |   |   |-- GET /<target>/_stats
    |   |   `-- GET /_stats
    |   |-- Field usage stats
    |   |   `-- GET /<index>/_field_usage_stats
    |   |-- Index segments
    |   |   |-- GET /<target>/_segments
    |   |   `-- GET /_segments
    |   |-- Index recovery
    |   |   |-- GET /<target>/_recovery
    |   |   `-- GET /_recovery
    |   `-- Index shard stores
    |       |-- GET /<target>/_shard_stores
    |       `-- GET /_shard_stores
    |-- Status management
    |   |-- Clear cache
    |   |   |-- POST /<target>/_cache/clear
    |   |   `-- POST /_cache/clear
    |   |-- Refresh
    |   |   |-- POST <target>/_refresh
    |   |   |-- GET <target>/_refresh
    |   |   |-- POST /_refresh
    |   |   `-- GET /_refresh
    |   |-- Flush
    |   |   |-- POST /<target>/_flush
    |   |   |-- GET /<target>/_flush
    |   |   |-- POST /_flush
    |   |   `-- GET /_flush
    |   `-- Force merge
    |       |-- POST /<target>/_forcemerge
    |       `-- POST /_forcemerge
    `-- Dangling indices
        |-- List dangling indices
        |   `-- GET /_dangling
        |-- Import dangling index
        |   `-- POST /_dangling/<index-uuid>?accept_data_loss=true
        `-- Delete dangling index
            `-- DELETE /_dangling/<index-uuid>?accept_data_loss=true

```