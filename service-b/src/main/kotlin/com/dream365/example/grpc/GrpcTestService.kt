package com.dream365.example.grpc

import com.dream365.example.ReactorTestServiceGrpc
import com.dream365.example.GetResultRequest
import com.dream365.example.GetResultResponse
import com.dream365.example.service.TestService
import io.grpc.Status
import io.grpc.StatusException
import org.lognet.springboot.grpc.GRpcService
import reactor.core.publisher.Mono

@GRpcService
class GrpcTestService(
    private val testService: TestService
) : ReactorTestServiceGrpc.TestServiceImplBase() {
    override fun getResult(request: Mono<GetResultRequest>): Mono<GetResultResponse> = request
        .flatMap {
            testService.getResultFromServiceC(it.msg)
        }

    override fun getResultWithException(request: Mono<GetResultRequest>): Mono<GetResultResponse> = Mono.error(
        Status.INTERNAL.withDescription("FAILED IN SERVICE B").asException()
    )
}