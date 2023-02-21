# camp2022_allin
스마일게이트 개발 캠프 2022 - 윈터 개발 캠프 2기 - All-IN
![로고](https://user-images.githubusercontent.com/102213509/220232051-71a7ceba-4d91-4bb8-81ea-722c7322e71d.png)

---
 

## 👨‍👩‍👧‍👦 팀 소개
<table>
  <tr>
    <td>
        <a href="https://github.com/loopy-lim">
         <img src = "https://avatars.githubusercontent.com/u/45393030?v=4" width="100px" />  
        </a>
    </td>
   <td>
        <a href="https://github.com/belljun3395">
         <img src = "https://avatars.githubusercontent.com/u/102807742?v=4" width="100px" />  
        </a>
    </td>
   <td>
        <a href="https://github.com/gkdud583">
         <img src = "https://avatars.githubusercontent.com/u/60775067?v=4" width="100px" />  
        </a>
    </td>
   <td>
        <a href="https://github.com/hayannn">
         <img src = "https://avatars.githubusercontent.com/u/102213509?v=4" width="100px" />  
        </a>
    </td>
  </tr>
  <tr>
    <td><b>임채승</b></td>
    <td><b>김종준</b></td>
    <td><b>정하영</b></td>
    <td><b>이하얀</b></td>
  </tr>
  <tr>
    <td>Front-End</td>
    <td>Back-End</td>
    <td>Back-End</td>
    <td>Back-End</td>
  </tr>
  <tr>
   <td><a href="https://github.com/loopy-lim">loopy-lim</a></td>
    <td><a href="https://github.com/belljun3395">belljun3395</td>
    <td><a href="https://github.com/gkdud583">gkdud583</td>
    <td><a href="https://github.com/hayannn">hayannn</td>
  </tr>
   <tr>
   <td> - </td>
    <td> - </td>
    <td><a href="https://velog.io/@gkdud583"/>velog</td>
    <td><a href="https://velog.io/@dlgkdis801"/>velog</td>
  </tr>
</table>

---

## 🚀 프로젝트 소개
### 서비스 소개
N.와 같은 서비스를 제공하는 실시간 스트리밍 웹어플리케이션

### 프로젝트 목표
NOW.를 기반으로 한 대량의 트래픽을 받는 웹 프로젝트 제작

- [프로젝트 세부 화면 보기](https://github.com/sgdevcamp2022/allin/tree/main/frontend/docs)

### 기술 스택
> # Frontend<br>
> * `React`
> * `React Router`
> * `Vite`
> * `Tailwind`
> * `STOMP`
> * `SockJS`
> * `Recoil`
> * `PNPM`
> * `MSW`
<br>

> # Backend<br>
> **🔐 인증인가 관련 서버**
> * `Java 11`
> * `Spring Boot 2.6.8`
> * `Spring Data JPA`
> * `Spring Security`
> * `JWT 0.11.2`
> * `Lombok`
> * `Xampp 3.3.0`
> * `MySQL 10.4.24`
> * `Redis`
> * `AWS EC2`
>
> #
> 
> **⌨️ 채팅, 신고 관련 서버**
> * `Java 17`
> * `SpringBoot 3.0.1`
> * `Spring Data JPA`
> * `Spring Batch`
> * `Spring Scheduler`
> * `Websocket, STOMP, SockJS`
> * `MySQL8`
> * `Redis`
> * `Mongo DB`
> * `Kafka`
> * `AWS EC2`
>
> #
> 
> **🎥 라이브 관련 서버**
> * `Java 11`
> * `SpringBoot 2.6.8`
> * `Spring Data JPA`
> * `MySQL`
> * `MariaDB`
> * `Nginx`
> * `Kafka`
> * `AWS EC2`
<br>

> # API Gateway<br>
> * `Spring Cloud Netflix Eureka`
> * `Spring Cloud Starter Netflix Zuul`

---

## 🛠️ 아키텍처
![image](https://user-images.githubusercontent.com/102213509/220328455-686170e2-e065-482a-9ac4-7fbdffdc1d0e.png)

---

## 👨‍💻 역할분담
### Frontend
#### 임채승
- [Web App](https://github.com/sgdevcamp2022/allin/tree/main/frontend)

### Backend
#### 김종준
- 라이브 관련 부분 구현
  - [미디어 서버](https://github.com/sgdevcamp2022/allin/tree/main/backend/media)
  - [CDN 서버](https://github.com/sgdevcamp2022/allin/tree/main/backend/cdn)
  - [매니저 서버](https://github.com/sgdevcamp2022/allin/tree/main/backend/manager)
  - [nginx 서버](https://github.com/sgdevcamp2022/allin/tree/main/backend/nginx)

- [API Gateway]()

#### 정하영
- 채팅, 신고 관련 부분 구현
  - [채팅 서버](https://github.com/sgdevcamp2022/allin/tree/main/backend/chat)
  - [신고 서버](https://github.com/sgdevcamp2022/allin/tree/main/backend/report)
  - [배치 서버](https://github.com/sgdevcamp2022/allin/tree/main/backend/batch)

#### 이하얀
- 인증인가 관련 부분 구현
  - [인증인가 서버](https://github.com/sgdevcamp2022/allin/tree/main/backend/Authserver7)

---

## 🗂️ 디렉터리 구조

```
sgdevcamp2022/allin
├──  docs
├──  backend
|    ├── Authserver7
|    ├── batch
|    ├── cdn
|    ├── chat
|    ├── manager
|    ├── media
|    ├── nginx
|    └── report
|
└── frontend
    ├── public
    └── src
        ├── assets
        ├── Atoms
        ├── Elements
        ├── Hooks
        ├── mocks
        ├── Pages
        |   ├── Home
        |   └── Sign
        └── utils            
```
#### 전체

|   Directory   |         Description          |
| ------------- | ---------------------------- |
| docs          |  설계, 기능 설명, API 문서    |
<br>


#### Frontend

|   Directory   |           Description           |
| ------------- | ------------------------------- |
| assets        | 내부 사이트에 들어갈 정적인 정보 |
| Atoms         | recoil의 State에 관한 정보       |
| Elements      | 페이지를 구성하는 부분           |
| Hooks         | 커스텀 React Hook               |
| mocks         | MSW서버 내용                    |
| Pages         | 실제로 보여지는 페이지 부분      |
| utils         | 공용적으로 사용하는 util의 모음  |
<br>

#### backend

|   Directory   |         Description          |
| ------------- | ---------------------------- |
| Authserver7   |  인증인가 서버               |
| batch         |  배치 서버                   |
| cdn           |  cdn 서버                    |
| chat          |  채팅 서버                   |
| manager       |  매니저 서버                 |
| media         |  미디어 서버                 |
| nginx         |  nginx 서버                  |
| report        |  신고 서버                   |

---

## 🪄 컨벤션
### 커밋 컨벤션

|                   메시지                   |             설명             |
| ------------------------------------------ | ---------------------------- |
| feat (feature)                             |  새로운 기능 추가            |
| fix (bug fix)                              |  버그 수정                   |
| style (formatting, missing semi colons, …) |  코드 포맷팅                 |
| refactor                                   |  코드 리팩토링               |
| test (when adding missing tests)           |  테스트 관련                 |
| chore (maintain)                           |  기타 수정                   |

