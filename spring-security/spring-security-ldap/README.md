# spring-security-ldap

To execute an LDAP search on the LDAP database embedded in the Spring Boot application, specify the server address configured by the prefix `spring.ldap.embedded`.

    ldapsearch -x -H http://localhost:8389
