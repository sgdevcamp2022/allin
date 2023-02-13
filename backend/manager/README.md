## Manager Server

### 소개

**매니저 서버는 방송 송출자를 위한 API를 제공하는 서버입니다.**

All-IN 팀이 Smilegate-WinterDev에서 구현하는 서비스는 네이버의 **.Now**를 기반하고 있습니다.

**.Now**는 한 명의 방송 송출자가 존재하고 그 방송을 시청하는 N명의 시청자가 존재하는 **1:N 구조**입니다.

**.Now**의 경우 방송 송출자가 연예인과 같은 특정인들로 한정되어 있어 방송 송출이 어떠한 방식으로 이루어지는지는 확인하는데 한계가 있었습니다.

그렇기에 매니저 서버는 **.Now**의 **1:N 구조**와 비슷한 **트위치**의 방송 송출 시스템을 기반으로 구현하였습니다.

### 기능
+ **[방송 송출자 관련 기능 (Publisher)](https://github.com/belljun3395/allin/blob/main/backend/manager/docs/domain/Publisher.md)**
  + 방송 송출자 등록
  + 방송 키 조회
  + 방송 키 수정
  + 방송 비밀번호 생성
  + 방송 비밀번호 초기화
  + 방송 비밀번호 사용
  + 방송 주소 조회
+ **[방송방 관련 기능 (Room)](https://github.com/belljun3395/allin/blob/main/backend/manager/docs/domain/Room.md)**
  + 방송방 생성
  + 방송방 정보 조회
  + 방송방 정보 수정
+ **[방송 (BroadCast)](https://github.com/belljun3395/allin/blob/main/backend/manager/docs/domain/BroadCast.md)**
  + 방송 시작
  + 방송 종료
  + 방송 목록 조회
+ **[매니저 (Manager)](https://github.com/belljun3395/allin/blob/main/backend/manager/docs/domain/Manager.md)**
  + 방송 시작
  + 방송 종료

### 기술 스택

+ Java 11
+ Springboot
+ MariaDB / H2


### POSTMAN DOCS

https://documenter.getpostman.com/view/17873656/2s935uGg1W

### QUESTION

https://github.com/belljun3395/allin/blob/main/backend/manager/docs/question/QUESTION.md

+ Mapper
+ 도메인간 연관관계
+ DB 테이블 설계
+ SQL
