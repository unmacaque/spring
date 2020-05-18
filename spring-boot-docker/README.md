# spring-boot-docker

1. Run `mvn package` to build a JAR file of the Spring Boot app
1. Run `mvn dockerfile:build` to build a layered Docker image of the app
1. Execute `docker run -p 8080:8080 -t unmacaque/spring-boot-docker` to start the containerized app

## Logging

### Fluentd

Add the parameter `--log-driver fluentd` to Docker to send logs to a running Fluentd instance.

### Logstash

Enable either of the profiles `logstash-tcp` or `logstash-udp`.

If required, the logstash endpoint can be changed by setting the environment variables like below:

```
LOGSTASH_HOST=localhost
LOGSTASH_PORT_TCP=9600
LOGSTASH_PORT_UDP=9514
```

## Prometheus

The application includes a Prometheus endpoint that can be scraped using this configuration:

```
  - job_name: 'spring-boot-docker'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['localhost:8080']
```
