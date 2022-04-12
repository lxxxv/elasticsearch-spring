```

cluster : 여러 대의 컴퓨터 혹은 요소들을 논리적으로 결합하여 전체를 하나의 컴퓨터, 혹은 하나의 구성 요소처럼 사용할 수 있게 해주는 기술
node : elasticsearch process = node

 

document : 단일 데이터 단위
index : document를 모아놓은 집합
indices : index를 모아놓은 집합
shard : 인덱스에 색인되는 문서들이 저장되는 논리적인 공간
segment : shard의 데이터들을 가지고 있는 물리적인 파일
mapping : rdbms와 비교하자면 스키마와 유사한 "개념" 적재될 json 문서들이 어떤 키와 어떤 형태의 값을 가지고 있는지 정의.
type : rdbms에서 table의 개념을 가졌으나 6버전에선 하나의 index에 하나의 type만, 7에서는 _doc로 고정, 8에서는 사라졌다.
discovery : 노드 간의 클러스터링을 위해 필요한 설정 정보

node, index, segment, shard, document 관계
    1 node = n index
    1 index = n shard = n segment
    node-1 (n index share)
    ├─ shard-1
    ├─ shard-3
    ├─ shard-4
    │  ├─ document (logical)
    │  │  ├─ segment (physical)
    │  │  ├─ segment (physical)
    │  │  ├─ segment (physical)
    │  │  ├─ segment (physical)
    │  │  ├─ segment (physical)
    node-2 (n index share)
    ├─ shard-0
    ├─ shard-1
    ├─ shard-2
    node-3 (n index share)
    ├─ shard-0
    ├─ shard-2
    ├─ shard-3
    ├─ shard-4

샤드로 나뉜 인덱스에 데이터가 저장되는 방식
    index
    shard0 {"no":1,"id":1230,"name":"park"}
    shard1 {"no":2,"id":4385,"name":"Kim"}
    shard2 {"no":3,"id":2358,"name":"Lee"}
    shard3 {"no":4,"id":3854,"name":"King"}
    shard4 {"no":5,"id":5432,"name":"Jeong"}

```