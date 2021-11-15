[ 요구 사항 ]
호스트 등록 관리

호스트 등록 관리 REST API를 제공해야 한다. (조회/등록/수정/삭제)
호스트 등록 필드는 name, address 이다.
name, address 는 중복되면 안 된다.
조회 결과 필드에는 호스트 등록/수정 시간을 포함해야 한다.
서버가 재시작되어도 등록된 호스트의 리스트는 유지되어야 한다.
호스트 등록은 100개로 제한한다.
등록된 호스트 Alive 상태 확인
Alive 상태 확인은 InetAddress.isReachable() 사용을 권장한다. (다른 방법도 가능)
호스트들의 Alive 상태를 조회하는 REST API를 제공해야 한다.
조회 결과 필드에는 현재 상태와 마지막 Alive 시간을 포함해야 한다.
조회의 단위는 한 호스트, 그리고 전체 호스트도 가능해야 한다.
Readme 작성
REST API 정의 내용은 Readme 에 정리한다.
설계 및 설명이 필요한 것은 Readme 에 정리한다.
요구 사항에서 만족하지 못한 항목은 Readme 에 정리한다.

[ 제약 및 권고 사항 ]
Java (8 이상) 1.8.0_301
MariaDB (버전 자유) 10.2.40
Gradle (버전 자유) 7.2
Spring-Boot 사용 (버전 자유) 2.5.6
DDL SQL은 파일로 Github에 추가

[ REST API 정의 ]
REST는 “Representational State Transfer” 의 약자로 자원을 이름(자원의 표현)으로 구분하여 해당 자원의 상태(정보)를 주고 받는 모든 것을 의미합니다.
API(Application Programming Interface)란 데이터와 기능의 집합을 제공하여 컴퓨터 프로그램간 상호작용을 촉진하며, 서로 정보를 교환가능 하도록 하는 것 입니다.
REST API는 REST 기반으로 서비스 API를 구현한 것 입니다.
REST API의 특징
사내 시스템들도 REST 기반으로 시스템을 분산해 확장성과 재사용성을 높여 유지보수 및 운용을 편리하게 할 수 있습니다.
REST는 HTTP 표준을 기반으로 구현하므로, HTTP를 지원하는 프로그램 언어로 클라이언트, 서버를 구현할 수 있습니다.
즉, REST API를 제작하면 델파이 클라이언트 뿐 아니라, 자바, C#, 웹 등을 이용해 클라이언트를 제작할 수 있습니다.

[ 설계 ]
1. 먼저 필요한 기능을 생각하고 디비를 구성합니다.
2. sql문을 사용해 MonitoringSB 테이블을 구현합니다.
3. application.properties파일과 build.gradle파일에 디비 연결을 위한 설정을 해줍니다.
4. MonitoringSB테이블의 컬럼에 맞춰 Host.java라는 dto에 해당하는 파일도 작성합니다.
5. rest api를 쓰기위한 컨트롤러 파일을 구성하고 각 기능에 맞는 어노테이션을 사용합니다.
6. 호스트 등록이 100명이 넘어가면 등록이 되지 않도록 처리합니다.
7. 호스트 등록과 수정시 로컬호스트에서 이름과 주소값을 받아와서 처리합니다.
8. 수정 시마다 현재시간을 받아서 수정시간 테이블을 업데이트 합니다.
9. 컨트롤러를 사용할 수 있게 HostService.java과 HostRepository.java파일을 만들어 줍니다.
10. 메인 클래스에 모니터링 메소드를 실행하도록 하고, 모니터링 메소드는 2초에 한번씩 작동하게 처리합니다.

[ 문제 ]
1. 뷰파일이 구현 안되서 테스트를 못했습니다.
초반엔 조회 값을 배열로 받아왔는데 현재 조회가 안되서 뷰파일로 구현해 봤지만 데이터를 조회할 수 없었습니다.
-> 뷰파일을 실행 시키기 위해 html을 열수있는config파일을 구성해봤으나 실행이 되지 않아 설정과 어노테이션 문제로 보여집니다.
2. 호스트들의 Alive 상태를 조회하는 REST API를 제공해야 한다.
모니터링을 메소드 하나로 처리하였기에 rest api를 사용하지 못했습니다.
->호스트 조회할 때 모니터링 정보도 컬럼에 있기에 조회 기능을 구현하지 않았습니다.
3. 메인클래스에 모니터링 메소드 실행시켰지만 실행이 되는지 모르겠습니다.
-> 메인클래스 말고 서버 연결시 무조건 실행되는 다른 클래스를 구현시켜 처리를 해야합니다.