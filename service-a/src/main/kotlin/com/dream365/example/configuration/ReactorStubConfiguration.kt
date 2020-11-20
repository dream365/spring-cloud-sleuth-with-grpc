package com.dream365.example.configuration

import com.dream365.example.ReactorTestServiceGrpc
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "service-b")
class ServiceBReactorStubConfiguration {
    lateinit var host: String
    lateinit var port: String

    @Bean
    fun serviceBChannel(): ManagedChannel = ManagedChannelBuilder
        .forAddress(host, port.toInt())
        .usePlaintext()
        .build()

    @Bean
    fun serviceBStub(serviceBChannel: ManagedChannel): ReactorTestServiceGrpc.ReactorTestServiceStub =
        ReactorTestServiceGrpc
            .newReactorStub(serviceBChannel)
}

@Configuration
@ConfigurationProperties(prefix = "service-d")
class ServiceDReactorStubConfiguration {
    lateinit var host: String
    lateinit var port: String

    @Bean
    fun serviceDChannel(): ManagedChannel = ManagedChannelBuilder
        .forAddress(host, port.toInt())
        .usePlaintext()
        .build()

    @Bean
    fun serviceDStub(serviceDChannel: ManagedChannel): ReactorTestServiceGrpc.ReactorTestServiceStub =
        ReactorTestServiceGrpc
            .newReactorStub(serviceDChannel)
}
