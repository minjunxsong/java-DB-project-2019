# java-DB-project-2019
2019년에 2학년 전공 기말 과제로 한 프로젝트입니다.
eclipse(java)와 PostgreSQL(DB)를 연동시켜서 만든 커뮤니티(게시판) 프로그램 입니다.

<초기 세팅 방법>

localhost:5432, 데이터베이스 이름은 postgres로 만들어준다.(그냥 처음에 포스트그레 다운할때 하라는대로 하면 저렇게 만들어진다.)
그리고 포스트그레 DB파일을 하나하나 추가 해줘야 하는데, 
users -> admintable -> freetable -> helptable -> tb_member -> tb_writespace -> comment ->helpcomment 순서로
추가 해주면된다. 
추가 하는 방법은 포스트그레에 접속하고 왼쪽 상단에 Query Tool 버튼을 누르고 Query창에 하나씩 복사 붙여넣기 하면된다.

이클립스와 포스트그레를 연동시켜주는 드라이버 파일을 다운로드 해서 추가 해야하는데 https://velog.io/@dlaskrgus8/PostgreSQL-JDBC-Driver-%EC%84%A4%EC%B9%98 이 사이트를 참고하면 된다.(저기서 제일 높은 버전이 JAVA8 버전인데 자기 버전이 저거보다 높을 경우에도 그냥 저 버전으로 하면 된다.)
이클립스에서 프로젝트에 우클릭을 한 후
Path에 jar 추가 : 프로젝트 우클릭 -> Properties -> Java Build Path -> Libraries -> Modulepath -> Add Library -> User Library -> User Libraries -> New -> 구별할 이름 넣기 (jdbc) -> ok -> 생성된 library 클릭 -> Add External JARs -> jar 파일 클릭 -> Apply and Close -> 새로 만들어진 Library 클릭 -> Finish -> Apply and Close
하면 세팅 끝이다.

주의!!!!!!실행할때는 test를 실행해야 메인화면을 갈 수 있다.
