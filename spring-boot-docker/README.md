# spring-boot-docker

Run the task `docker` to build a layered Docker image of the app.

Execute the following command to start the containerized app.

    docker run -p 8080:8080 -t unmacaque/spring-boot-docker

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

## Prometheus

The application includes a Prometheus endpoint that can be scraped using this configuration:

```
scrape_configs:
  - job_name: 'spring-boot-docker'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['localhost:8080']
```
