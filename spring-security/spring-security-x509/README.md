# spring-security-x509

Mutual TLS authentication with Spring Security.

1. Run `./openssl.sh`
1. Start `spring-security-x509`

Example authenticated request using _cURL_:

    curl --cert client.crt --key client.key --cacert rootca.crt https://localhost:8443
