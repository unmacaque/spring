# spring-security-oauth2-authorization-server

Configuration snippet to register this authorization server as an OAuth 2.0 provider:

```
spring:
  security:
    oauth2:
      client:
        provider:
          authorization-server:
            issuer-uri: http://127.0.0.1:8765
        registration:
          authorization-server:
            authorization-grant-type: authorization_code
            client-name: Spring Security OAuth2 Authorization Server
            client-id: client
            client-secret: secret
            redirect-uri: "{baseUrl}/login/oauth2/code/"
            scope: openid,profile
```
