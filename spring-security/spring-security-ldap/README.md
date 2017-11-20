# Instructions

First, run `mvn spring-boot:run` to start the Spring Boot application.

To execute an LDAP search, specify the server configured by the prefix `spring.ldap.embedded`.

    ldapsearch -x -H http://localhost:8389
