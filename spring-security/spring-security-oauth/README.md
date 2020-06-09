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

## Integrations

### Bitbucket

Login to <https://bitbucket.org/> and go to _Bitbucket Settings_. From _Access Management_ choose _OAuth_. Click on the button _Add consumer_. In the next dialog, enter a value for _Callback URL_, which must correspond to the `redirect-uri` property in the Spring Boot configuration. The consumer must have at least a permission on _Email_ to allow login on behalf of the Bitbucket Account.

```
spring:
  security:
    oauth2:
      client:
        registration:
          bitbucket:
            authorization-grant-type: authorization_code
            client-name: Bitbucket
            client-id: <key>
            client-secret: <secret>
            redirect-uri: http://localhost:8080/login/oauth2/code/
            scope: email
```

### GitLab

Login to <https://gitlab.com>. Go to the [Applications]( https://gitlab.com/profile/applications) section in the User Settings.

Create a new application using the `redirect-uri` property as the value for _Redirect URI_. Make sure the scope _openid_ is checked. Press _Save application_ to create the application and show the client ID and secret.

```
spring:
  security:
    oauth2:
      client:
        registration:
          gitlab:
            authorization-grant-type: authorization_code
            client-name: GitLab
            client-id: <key>
            client-secret: <secret>
            redirect-uri: http://localhost:8080/login/oauth2/code/
            scope: openid
```
