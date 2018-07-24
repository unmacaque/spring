# spring-security-oauth

Register an account with the OAuth provider of your choice, e.g. GitHub.

Using either provider, create an OAuth app. For the rediction URL, enter `http://localhost:8080/login/oauth2/code`

The provider will generate a client ID and client secret. Fill these in the properties:

```
spring.security.oauth2.client.registration.myprovider.client-id
spring.security.oauth2.client.registration.myprovider.client-secret
```

In the Spring Boot application, configure the OAuth2 authentication provider. See `application.yml` to view an example configuration of an OpenID Connect provider. Note: Some providers such as GitHub are already configured out of the box.

Finally, run `mvn spring-boot:run`.
