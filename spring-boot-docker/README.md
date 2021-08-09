# spring-boot-docker

1. Run the task `bootJar` to build a JAR file of the Spring Boot app
1. Run the task `dockerBuild` to build a layered Docker image of the app
1. Execute `docker run -p 8080:8080 -t unmacaque/spring-boot-docker` to start the containerized app

## Logging

### Loki

First, start a [Loki](https://grafana.com/oss/loki/) instance locally.

Install the [Loki Docker Driver](https://grafana.com/docs/loki/latest/clients/docker-driver/) using this command:

    docker plugin install grafana/loki-docker-driver:latest --alias loki --grant-all-permissions

Start the Spring Boot application Docker image while specifying the `loki` log driver:

```
docker run
    --rm \
    --name=spring-boot-docker \
    --log-driver=loki \
    --log-opt loki-url="http://localhost:3100/loki/api/v1/push" \
    -p 8080:8080 \
    unmacaque/spring-boot-docker:latest
```

Example LogQL statement to query the log lines of this container:

    {container_name="spring-boot-docker"} | json | level="INFO" | line_format "{{.message}}"

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
scrape_configs:
  - job_name: 'spring-boot-docker'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['localhost:8080']
```
