# 회원가입

- method: POST
- url: /api/v1/auth/signup

## 요청

```jsx
{
    "email":"1234@naver.com", // 가입할 이메일
    "password":"1234", // 비밀번호
    "userName":"hayan", // 실명
    "nickName":"dd" // 닉네임
}
```

## 응답

- 해당 부분에서는 실패 응답은 공통 응답으로 구현 되었으나, 성공 응답은 코드 작성했음에도 적용이 안되어 이렇게 응답이 되고 있는 상태입니다!
- 성공 시
- HTTP Status : 201 Created

```json
{
    "email": "1234@naver.com",
    "userName": "hayan",
    "nickName": "dd",
    "authorities": [
        {
            "authorityStatus": "ROLE_USER"
        }
    ]
}
```

- 성공 응답 업데이트

```json
{
    "result": "success",
    "data": {
        "email": "1234@naver.com",
        "userName": "hayan",
        "nickName": "dd"
    }
}
```

- 실패 시
- HTTP Status : 400 Bad Request

```json
{
    "timestamp": "2023-02-07T06:34:02.698+00:00",
    "code": "4001000",
    "error": "RuntimeException",
    "message": "이미 가입되어 있는 이메일입니다."
}
```

# 로그인

- method: POST
- url: /api/v1/auth/login

## 요청

```jsx
{
    "email":"1234@naver.com", //가입한 이메일
    "password":"1234" //가입한 비밀번호
}
```

## 응답

- 해당 부분에서는 실패 응답은 공통 응답으로 구현 되었으나, 성공 응답은 코드 작성했음에도 적용이 안되어 이렇게 응답이 되고 있는 상태입니다!
- 성공 시
- HTTP Status : 201 Created

```json
{
    "grantType": "bearer",
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY3NTc1MzQ5OX0.aTAjLRTY1Y47-PoX8_J4frxneJBxEQzvdhA4q0Eg738v32LAfoM4Ga_p_2vQTo3I_aJ-NxRpuPUtTjpku9gikw",
    "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NzYzNTY0OTl9.Y2gnlkC7gRIOxqPUWHbcVxK-6RX-ag_cQg8OkZiEST2DbxfpISCjERA8l-2PY9piBhuUPxgE_mn0u2ryx1Qkog",
    "accessTokenExpiresIn": 1675753499984
}
```

- 성공 응답 업데이트

```json
{
    "result": "success",
    "data": {
        "grantType": "bearer",
        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY3NjI5OTMwOH0.I5zHVbyAlq9CkeDfEVV7WM7r-y5ym5pNp5viGJZlY9MAWhd7sMWBE7qKU2DwDFpM93L8ALe_MjxsGinkqLOoWg",
        "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NzY5MDIzMDh9.CEmMWyWXfkOmP-qWgVh6l7agS9OxkLIiruJyv7z9FTYJVt_ccrPf1aS6fLqvLT2cN3ICGCQd_DcUEbK4_Azqrw",
        "accessTokenExpiresIn": 1676299308145
    }
}
```

- 실패 시
- HTTP Status : 400 Bad Request

```
{
    "timestamp": "2023-02-07T06:35:18.587+00:00",
    "code": "4001000",
    "error": "BadCredentialsException",
    "message": "자격 증명에 실패하였습니다."
}
```

# 로그아웃

- method: GET
- url: /api/v1/auth/user/logout

## 요청

- 요청은, 로그인 이후 발급된 accessToken으로 진행됩니다.

![Untitled](https://user-images.githubusercontent.com/45393030/219909783-46c33785-771d-4ac4-97d4-7489dfd15cfd.png)

## 응답

- 성공 시
- HTTP Status : 200 Ok

```json
로그아웃 성공
```

- 성공 응답 업데이트
- HTTP Status : 201 Created

```json
{
    "result": "success",
    "data": null
}
```

- 실패 시
- 해당 부분은 실패 응답이 적용이 되지 않는 것인지, 모든 내용이 다 응답되어 이 부분을 수정하려 했으나 어려움이 있어 우선은 이렇게 응답이 되고 있는 상태

```json
{
    "timestamp": "2023-02-07T06:37:50.226+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "trace": "java.lang.RuntimeException: 로그아웃 된 사용자 입니다.\r\n\tat me.ver.Authserver7.jwt.JwtFilter.doFilterInternal(JwtFilter.java:32)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n\tat org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n\tat org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n\tat org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n\tat org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n\tat org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n\tat org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n\tat org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n\tat org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n\tat org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n\tat org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:354)\r\n\tat org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:267)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)\r\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)\r\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)\r\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360)\r\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)\r\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:890)\r\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1743)\r\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\r\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n\tat java.base/java.lang.Thread.run(Thread.java:829)\r\n",
    "message": "로그아웃 된 사용자 입니다.",
    "path": "/api/v1/auth/user/logout"
}
```

# 토큰 재발급

- method: POST
- url: /api/v1/auth/reissue

## 요청

- 로그인 이후 발급받은 accessToken과 refreshToken을 Body에 넣어 요청, 인증이 되면 재발급이 됩니다.

```json
{
     "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY3NjI5OTcyMH0.djVUzuC9FsmPTBeSPyS8WIcUngE-zNdoBdbf73jSLx3VwLLHRqLKGFEtTX7lPddFgcSic5RTPT1csar0ljOaAg",
     "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NzY5MDI3MjB9.V-GEk9FuLbjtO0iZdQbUoUoeLtN0AlOGOLf3eXo-6hxJn6l-10No5UvKBpOSbR3fSe8rbuA4jnJzmM4YzZ_Kvw"
}
```

## 응답

- 성공
- HTTP Status : 200 Ok

```json
{
    "grantType": "bearer",
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY3NTc1Mzc3MX0.NuGdIGk55y1Bc2nLIEk0M6jYS3hX_iEReZrdYTD0Puafv3x6FzMF2abLL9t5pkJhui1bU7-rvGOxXLgTwJ7qVg",
    "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NzYzNTY3NzF9.8_0e6NKzjkY5L_qCWWw8gRceomwQRQodsds_YEErFkOhsfWUqTOuN0Kf6_aVJotqcrcSjFAjg4FbPZfWSt6tNw",
    "accessTokenExpiresIn": 1675753771916
}
```

- 성공 응답 업데이트
- HTTP Status : 201 Created

```json
{
    "result": "success",
    "data": {
        "grantType": "bearer",
        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY3NjI5OTczNn0.0MEqiujVVRGr3434MbNdBOHtGiDa0lCvuPGfnbxnWs7-dU7auIdvAw0kiS6vctHSTqBm9cU6OVRKctTRqHBLSw",
        "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NzY5MDI3MzZ9.5-4YkSK4Wrept5HrqEqu804BaavOoXWT-lSFDDkdem-JDzcipquuKANDP3rU3mKoUPEDXLN1RMZQjsdOylHRXQ",
        "accessTokenExpiresIn": 1676299736599
    }
}
```

- 실패
- HTTP Status : 400 Bad Request

```json
{
    "timestamp": "2023-02-07T06:40:57.205+00:00",
    "code": "4001000",
    "error": "RuntimeException",
    "message": "토큰의 유저 정보가 일치하지 않습니다."
}
```

# 회원정보 조희

- method: GET
- url: /api/v1/auth/user/me

## 요청

- 요청은, 로그인 이후 발급된 accessToken으로 진행됩니다.

![Untitled](https://user-images.githubusercontent.com/45393030/219909800-b6ecf75b-7714-4bcc-a6be-4cb499285792.png)

## 응답

- 성공
- HTTP Status : 200 Ok

```json
{
    "email": "1234@naver.com",
    "userName": "hayan",
    "nickName": "dd",
    "authorities": [
        {
            "authorityStatus": "ROLE_USER"
        }
    ]
}
```

- 성공 응답 업데이트
- HTTP Status : 201 Created

```json
{
    "result": "success",
    "data": {
        "email": "1234@naver.com",
        "userName": "hayan",
        "nickName": "dd"
    }
}
```

- 실패
- HTTP Status : 400 Bad Request

```json
{
    "timestamp": "2023-02-07T06:42:49.293+00:00",
    "code": "4001000",
    "error": "AccessDeniedException",
    "message": "접근이 거부되었습니다."
}
```

# 회원정보 수정

- method: PUT
- url: /api/v1/auth/user/update


## 요청

- 요청은 accessToken과 Body로 이루어집니다.(현재 userName, nickName, password 수정 가능)

![Untitled](https://user-images.githubusercontent.com/45393030/219909808-fc4dff16-0d13-489a-b732-765208eeede4.png)

```json
{
    "nickName":"ttt",
    "password":"4321"
}
```

## 응답

- 성공 : 패스워드 정보는 민감 정보이기 때문에 응답에서 보여주지 않으나, DB에서 변경 여부는 확인 가능합니다.
- HTTP Status : 200 Ok

```json
{
    "email": "1234@naver.com",
    "userName": "hayan",
    "nickName": "ttt", //이전 : dd
    "authorities": [
        {
            "authorityStatus": "ROLE_USER"
        }
    ]
}
```

- 성공 응답 업데이트
- HTTP Status : 201 Created

```json
{
    "result": "success",
    "data": {
        "email": "1234@naver.com",
        "userName": "hayan",
        "nickName": "ttt"
    }
}
```

- 실패
- HTTP Status : 400 Bad Request

```json
{
    "timestamp": "2023-02-07T06:46:03.167+00:00",
    "code": "4001000",
    "error": "AccessDeniedException",
    "message": "접근이 거부되었습니다."
}
```

# 회원탈퇴

- method: DELETE
- url: /api/v1/auth/user/me

## 요청

- 이 역시, 로그인 시 발급받은 accessToken으로 진행됩니다.

![Untitled](https://user-images.githubusercontent.com/45393030/219909818-670da634-177b-4c7d-b6f4-d33633a5446b.png)


## 응답

- 성공
- HTTP Status : 200 Ok

```json
회원 탈퇴 성공
```

- 성공 응답 업데이트
- HTTP Status : 201 Created

```json
{
    "result": "success",
    "data": null
}
```

- 실패

→ 이 역시 로그아웃 실패 응답과 같이 실패 응답이 적용이 되지 않는 것인지, 모든 내용이 다 응답되어 이 부분을 수정하려 했으나 어려움이 있어 우선은 이렇게 응답이 되고 있는 상태

```json
{
    "timestamp": "2023-02-07T06:48:08.605+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "trace": "java.lang.RuntimeException: 로그아웃 된 사용자 입니다.\r\n\tat me.ver.Authserver7.jwt.JwtFilter.doFilterInternal(JwtFilter.java:32)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n\tat org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:103)\r\n\tat org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:89)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n\tat org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)\r\n\tat org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n\tat org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:110)\r\n\tat org.springframework.security.web.context.SecurityContextPersistenceFilter.doFilter(SecurityContextPersistenceFilter.java:80)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n\tat org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:55)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:336)\r\n\tat org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:211)\r\n\tat org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:183)\r\n\tat org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:354)\r\n\tat org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:267)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)\r\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)\r\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)\r\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360)\r\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)\r\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:890)\r\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1743)\r\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\r\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n\tat java.base/java.lang.Thread.run(Thread.java:829)\r\n",
    "message": "로그아웃 된 사용자 입니다.",
    "path": "/api/v1/auth/user/me"
}
```