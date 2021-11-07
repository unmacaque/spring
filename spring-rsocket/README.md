# spring-rsocket

[RSocket](https://rsocket.io/) provides a protocol for Reactive Streams semantics between client-server, and server-server communication.

## Interaction Models

- **Request-Response** - send one message and receive one back.
- **Request-Stream** - send one message and receive a stream of messages back.
- **Channel** - send streams of messages in both directions.
- **Fire-and-Forget** - send a one-way message.

The RSocket interaction type that an `@MessageMapping` method supports is determined from the cardinality of the input.

|Interaction|Input|Output|
|---|---|---|
|Request-Response|`Mono<T>`|`Mono<U>`|
|Request-Stream|`Mono<T>`|`Flux<U>`|
|Channel|`Flux<T>`|`Flux<U>`|
|Fire-and-Forget|`Mono<T>`|`Mono<Void>`|

## RSocketResponder

The interaction type is determined implicitly from the cardinality of the input and output.

## Resources

- <https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.rsocket>
- <https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#rsocket>
- <https://spring.io/blog/2020/03/02/getting-started-with-rsocket-spring-boot-server>
- <https://spring.io/blog/2020/03/09/getting-started-with-rsocket-spring-boot-client>
