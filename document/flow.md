```

1. elk download
env : windows
https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.17.2-windows-x86_64.zip
https://artifacts.elastic.co/downloads/kibana/kibana-7.17.2-windows-x86_64.zip
https://artifacts.elastic.co/downloads/logstash/logstash-7.17.2-windows-x86_64.zip

2. unzip

3. add path
ELASTICSEARCH_HOME, ES_JAVA_HOME, KIBANA_HOME, LOGSTASH_HOME

4. config file check
ELASTICSEARCH_HOME/config/elasticsearch.yml
KIBANA_HOME/config/kibana.yml

5. service add
elasticsearch-service.bat install
nssm.exe install elasticsearch-kibana-service-x64
displayname = Elasticsearch Kibana 7.17.2 (elasticsearch-kibana-service-x64)
esc = Elasticsearch 7.17.2 Windows Service - https://elastic.co

6. nori plugin add
elasticsearch-plugin install analysis-nori
GET _analyze
{
  "tokenizer": "nori_tokenizer",
  "text": [
    "동해물과 백두산이"
  ]
}

https://esbook.kimjmin.net/06-text-analysis/6.7-stemming/6.7.2-nori


GET /index(tablename)/_mapping

```