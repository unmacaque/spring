# spring-security-x509

Mutual TLS authentication with Spring Security.

Run with Gradle task `bootRun`.

Example authenticated request using _cURL_:

    curl --cert client.pem --key client.key --cacert trust.pem https://localhost:8443
