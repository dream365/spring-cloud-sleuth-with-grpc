package com.dream365.example.configuration

import com.dream365.example.ReactorTestServiceGrpc
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cloud.sleuth.instrument.grpc.GrpcManagedChannelBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "service-c")
class ServiceCReactorStubConfiguration {
    lateinit var host: String
    lateinit var port: String

    @Bean
    fun serviceCChannel(tracingManagedChannelBuilderCustomizer: GrpcManagedChannelBuilderCustomizer): ManagedChannel {
        val builder = ManagedChannelBuilder
            .forAddress(host, port.toInt())
            .usePlaintext()

        tracingManagedChannelBuilderCustomizer.customize(builder)

        return builder.build()
    }

    @Bean
    fun serviceCStub(serviceCChannel: ManagedChannel): ReactorTestServiceGrpc.ReactorTestServiceStub =
        ReactorTestServiceGrpc
            .newReactorStub(serviceCChannel)
}
