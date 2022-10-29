# spring-native

_Last verified with GraalVM 22.3_

**Note**: The native tasks consume a lot of memory and CPU resources

1. Install [GraalVM with native-image support](https://graalvm.github.io/native-build-tools/latest/graalvm-setup.html)
2. Set `JAVA_HOME` to `/usr/lib/jvm/java-17-graalvm` or wherever GraalVM is located on your machine
3. Run the task `nativeTest` (optional)
4. Run the task `nativeCompile`
5. Run the executable `/build/native/nativeCompile/spring-native`
