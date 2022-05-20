# spring-security-x509

Mutual TLS authentication with Spring Security.

1. Run `./openssl.sh`
1. Start `spring-security-x509`

Example authenticated request using _cURL_:

    curl --cert client.pem --key client.key --cacert trust.pem https://localhost:8443
