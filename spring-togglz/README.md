# spring-togglz

Start the application and open <http://localhost:8080/togglz-console> to use the integrated Togglz console.

Open <http://localhost:8080/h2-console> to inspect the embedded database. JDBC URL is `jdbc:h2:mem:testdb`

Activate the `jdbc` profile to configure a `JDBCStateRepository` and have Togglz persist state information in the embedded JDBC database.
