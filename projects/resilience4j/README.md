# resilience4j

[Resilience4j](https://resilience4j.github.io/resilience4j/) is a lightweight fault tolerance library inspired by Netflix Hystrix, but designed for functional programming.

## Components

| Component      | Description                                                          |
|----------------|----------------------------------------------------------------------|
| CircuitBreaker | Aggregates the outcome of a call and react on failure rates          |
| Bulkhead       | Limit the number of concurrent execution of a code path              |
| RateLimiter    | Limit number of invocations and queue up or deny over limit requests |
| Retry          | Re-attempt an invocation in case of failure and eventually recover   |
| TimeLimiter    | Stop an invocation after a configurable duration has passed          |

## CircuitBreaker

Can have one of the following states:

- `CLOSED`, starts in this state, meaning invocation is fully evaluated
- `OPEN`, invocations are not evaluated and fail directly instead
- `HALF_OPEN`, after a specified time in `OPEN` moves to this state
- `FORCED_OPEN`, must be manually set, invocations will never be evaluated
- `DISABLED`, must be manually set, invocations will always be evaluated

Basic concept of a CircuitBreaker:

- It internally stores the number of successful and failed invocations in a ring buffer. This means it will store the outcome of as much invocations as the size of the ring buffer.
- Once the value of `bufferedCalls` reaches `maxBufferedCalls`, the CircuitBreaker starts to compute the `failureRate`. If the `failureRate` is greater or equal than the `failureRateThreshold` threshold, then it moves to the `OPEN` state.
- After a certain amount of time has passed after the CircuitBreaker moved to the `OPEN` state, it will transition to the `HALF_OPEN` state. It evaluates the `failureRate` using a different ring buffer. If the `failureRate` is lower than the threshold, the state is changed to `CLOSED`, otherwise it will transition back to `OPEN`.

Spring Boot notes:

- `CircuitBreaker` beans can be created via properties.
- `HealthIndicator` instance must be explicitly enabled using the property `registerHealthIndicator`
- The mapping of the CircuitBreaker states to Spring Boot Actuator health status is as follows:
    - `CLOSED` = `UP`
    - `OPEN` = `DOWN`
    - `HALF_OPEN` = `UNKNOWN`

