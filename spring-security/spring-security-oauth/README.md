# spring-security-oauth

1. Register an account with the OAuth provider of your choice, e.g. GitHub
1. Using either provider, create an OAuth app. For the rediction URL, enter `http://localhost:8080/login/oauth2/code`
1. The provider will generate a client ID and client secret. Fill these in the properties `spring.security.oauth2.client.registration.<provider>.client-id` and `spring.security.oauth2.client.registration.<provider>.client-secret`.
1. In the Spring Boot application, configure the OAuth2 authentication provider. Note that some providers such as GitHub are already configured out of the box.
1. Run `mvn spring-boot:run`
