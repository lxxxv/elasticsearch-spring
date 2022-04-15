```

Analyer 는 크게 Char Filters, Tokenizer, Token Filters 로 나뉩니다.
'char_filter' 는 0~3개로 구성을 합니다. 
한 문장이 들어왔을 때 각 문자의 필터 역할을 하며 추가, 삭제, 변경이 할 수 있으며 매핑이나 html 태그 필터, 사용자가 직접 custom 하여 맞춤 char_filter의 기능이 있습니다. 

'tokenizer' 는 정확히 1개로 구성되어야합니다. 
기능은 'char filter'의 과정을 거치고 나서 문장의 각 단어들을 tokenizer 옵션에 맞게 token 으로 자르는 역할을 합니다. 대표적으로 'standard', 'letter', 'Lowercase', 'whitespace' 등이 있으며 예시로 'standard tokenizer'를 사용한다면 한 문장에서 tokenizer 내에서 사용하는 알고리즘을 통해 단어별로 token이 나눠지게 됩니다.
gd
'filter'는 'Token Filter'를 의미합니다. 0개 이상으로 구성을 합니다. 
'tokenizer' 과정에서 나눠진 각 token 들을 말그대로 filter하는 과정입니다. 토큰의 '동의어'나 'ngram', 'stoptoken 등 많은 filter의 종류가 있으며 토큰들의 추가하거나 삭제, 변경이 가능합니다.
"analyzer" : {
   "char_filter": "html_strip",     // html 태그 제거
   "tokenizer": "whitespace"        // whitespace 기준 토큰 생성
   "filter": "lowercase"            // 소문자로 필터링
}
1. char_filter : "This is test Keyword. Test Keyword right?"  --> 문장에서 태그가 사라집니다.
2. tokenizer : [This, is, test, Keyword, Test, Keyword, right?]  --> 띄어쓰기 기준으로 자릅니다.
3. filter : [this, is, test, keyword, test, keyword, right?]   --> Token들이 lowercase 필터됩니다.


node
RPM, DEB, TAR 설치 유형 중 TAR형태의 배포형은 압축 해제하여 PORT 설정을 통해 하나의 물리적 환경에 여러 elasticsearch process를 실행 할 수 있다.
이와 같은 환경으로 설치하게 되면 쉽게 클러스터링이 가능하다는 의미이다.
elasticsearch.yml에서 cluster.name 그룹에 node.name 을 다르게 하여 하나의 클러스터로 사용 할 수 있다.


노드의 역할
각각 하나의 역할만 할 수 있는것은 아니고 한 번에 여러 개의 역할을 할 수 있다.
마스터 노드
클러스터 상태, 메타데이터 관리
클러스터 내에서 메타데이터를 관리하는 마스터 노드는 한 대이고, 여러대의 마스터 역할 노드를 구성한다면 나머지는 현재 실행중인 마스터 노드에 장애가 발생했을 때 새로운 마스터가 될 수 있는 후보 노드이다.
데이터 노드
사용자의 문서를 실제로 저장
인제스트 노드
사용자의 문서가 저장되기 전 문서 내용을 사전 처리
코디네이트 노드
사용자의 요청을 데이터 노드로 전달하고, 다시 데이터 노드로부터 결과를 취합



인덱스 생성에서의 타입
7.0에서 인덱스 APIs는 _id 자동 생성을 위한 {index}/_doc 경로와 명시적인 ID를 가진 {index}/_doc/{id}와 함께 호출을 해야만 한다.
7.0 버전에서 _doc는 도큐먼트 타입 대신 엔드 포인트명을 표시한다.
_doc 컴포넌트는 앞으로 도큐먼트 index, get, delete APIs 경로의 영구적인 부분이며 8.0에서도 제거되지 않았다.

검색에서의 타입
_search, _msearch, _explain과 같은 검색 API를 사용할 때, 타입이 URL에 포함되어서는 안된다.
추가적으로, _type 필드는 쿼리, 집계 또는 스크립트에서 사용해서는 안된다.

응답에서의 타입
도큐먼트와 검색 APIs는 응답에서 응답 파싱의 깨짐을 방지하기 위하여 _type키를 계속해서 반환할 것이다.
하지만 타입 키는 더 이상 사용하지 않는것으로 간주되고, 더 이상 참조되지 않는다.
8.0 버전의 응답에서는 타입이 완전히 삭제 되었다.
더 이상 deprecated typed API가 사용된다면, 인덱스의 매핑 타입은 평소와 같게 반환을 할 것이다.
하지만, typeless APIs를 사용한다면, 응답에 더미 타입인 _doc을 반환할 것이다.
예를 들면, 아래 typeless get 호출은 항상 타입값으로 _doc을 반환한다.
또한 매핑이 my_type과 같은 커스텀 타입 명을 가지더라도 _doc을 반환한다.

인덱스 동작 방식 변경
인덱스의 설정 없이 다이나믹 매핑으로 색인을 하면 프라이머리 샤드 5개 레플리카 샤드 1개가 기본으로 생성되나,
7.x 버전부터는 프라이머리 샤드 1개, 레플리카 샤드 1개만 생성이 된다. 인덱스를 만들 때 미리 샤드의 개수를 지정한다면 문제가 없겠지만,
그렇지 않은 경우 기본 설정이 운영에 문제가 될 수 있다.

세드먼트 동작 방식 변경
refresh_interval로 설정한 시간 이후에 메모리에서 디스크로 쓰이는데
7.x 버전에서는 인덱스에 검색 요청이 30초 이상 들어오지 않으면 refresh_interval에 설정된 시간을 무시하고 문서를 메모리에 남겨둔다.
어차피 검색되지 않을 문서를 디스크에 쓰지 않고 메모리에 모아둠으로써 디스크I/O를 줄이는 것이다.

매핑 타입의 변경
6.x 버전 이후로는 하나의 인덱스에 하나의 매핑 타입만 가질 수 있다.
멀티 타입을 허용하지 않게 된 이유 중 하나는 인덱스에 존재하는 서로 다은 타입에서 동일한 이름의 JSON문서 필드를 만들 수 있어서 의도치 않은 검색 결과가 나타나는 문제 발생

```