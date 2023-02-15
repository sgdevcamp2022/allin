## Manager Server

### 간단 소개

**매니저 서버는 방송 송출자를 위한 API를 제공하는 서버입니다.**

All-IN 팀이 Smilegate-WinterDev에서 구현하는 서비스는 OBS를 통해 방송을 송출합니다.

또 인증 받은 스트리머만 방송을 송출 할 수 있습니다.

매니저 서버는 위와 같은 서비스의 요구사항을 충족 하기 위하여 개발되었습니다.

프로젝트에 대한 자세한 사항은 [링크](https://github.com/belljun3395/allin/tree/belljun/backend/manager/docs)를 통해 소개하도록 하겠습니다.

### 기능
+ **[방송 송출자 관련 기능 (Publisher)](https://github.com/belljun3395/allin/blob/belljun/backend/manager/docs/domain/Publisher.md)**
  + 방송 송출자 등록
  + 방송 키 조회
  + 방송 키 수정
  + 방송 비밀번호 생성
  + 방송 비밀번호 초기화
  + 방송 비밀번호 사용
  + 방송 주소 조회
+ **[방송방 관련 기능 (Room)](https://github.com/belljun3395/allin/blob/belljun/backend/manager/docs/domain/Room.md)**
  + 방송방 생성
  + 방송방 정보 조회
  + 방송방 정보 수정
+ **[방송 (BroadCast)](https://github.com/belljun3395/allin/blob/belljun/backend/manager/docs/domain/BroadCast.md)**
  + 방송 목록 조회
+ **[매니저 (Manager)](https://github.com/belljun3395/allin/blob/belljun/backend/manager/docs/domain/Manager.md)**
  + 방송 시작
  + 방송 종료

### 기술 스택

+ Java 11
+ Springboot
+ MariaDB / H2

### 클래스 다이어그램
![exported_from_idea drawio (1)](https://user-images.githubusercontent.com/102807742/218646181-26213c8b-502c-4969-b38c-ff897c68b940.png)


### POSTMAN DOCS

https://documenter.getpostman.com/view/17873656/2s935uGg1W

### QUESTION

https://github.com/belljun3395/allin/blob/belljun/backend/manager/docs/question/QUESTION.md

+ Mapper
+ 도메인간 연관관계
+ DB 테이블 설계
+ SQL
