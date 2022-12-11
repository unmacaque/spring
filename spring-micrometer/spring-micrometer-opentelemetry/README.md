# spring-opentelemetry

1. Start an [otel-collector](https://opentelemetry.io/docs/collector/getting-started/), e.g. using Docker
2. Start the application with profile `otel-collector`
3. Send a request to <http://localhost:8080>
4. Observe the output of the otel-collector
