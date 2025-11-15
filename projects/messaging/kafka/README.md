# kafka

Start a Kafka broker, for example by using the [apache/kafka](https://hub.docker.com/r/apache/kafka) Docker image.

    docker run -d --name broker -p 9092 apache/kafka:latest

Once the container is started, start the Spring Boot applications in this order.

1. Start the `consumer` application
2. Start the `producer` application

Observe the logging output of the consumer application.
