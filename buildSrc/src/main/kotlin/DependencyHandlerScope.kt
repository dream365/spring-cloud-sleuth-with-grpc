import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.springBoot(
    module: String
) = "org.springframework.boot:spring-boot-$module"

fun DependencyHandlerScope.grpc(
    module: String,
    version: String = Ver.grpc
) = "io.grpc:grpc-$module:$version"

fun DependencyHandlerScope.protobufJava(
    version: String = Ver.protobuf
) = "com.google.protobuf:protobuf-java:$version"

fun DependencyHandlerScope.reactorGrpcStub(
    version: String = Ver.reactorGrpc
) = "com.salesforce.servicelibs:reactor-grpc-stub:$version"

fun DependencyHandlerScope.grpcSpringBootStarter(
    version: String = Ver.grpcSpringBootStarter
) = "io.github.lognet:grpc-spring-boot-starter:$version"