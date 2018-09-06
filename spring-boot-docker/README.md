# spring-boot-docker

1. Run `mvn package` to build a JAR file of the Spring Boot app
1. Run `mvn dockerfile:build` to build a Docker image of the app
1. Execute `docker run -p 8080:8080 -t unmacaque/spring-boot-docker` to start the containerized app

Add the parameter `--log-driver fluentd` to Docker to send logs to a running Fluentd instance.

The application includes a Prometheus endpoint that can be scraped using this configuration:

```
  - job_name: 'spring-boot-docker'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['localhost:8080']
```
