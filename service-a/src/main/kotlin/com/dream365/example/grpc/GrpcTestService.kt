package com.dream365.example.grpc

import brave.Tracer
import com.dream365.example.ReactorTestServiceGrpc
import com.dream365.example.GetResultRequest
import com.dream365.example.GetResultResponse
import com.dream365.example.service.TestService
import org.lognet.springboot.grpc.GRpcService
import reactor.core.publisher.Mono

@GRpcService
class GrpcTestService(
    private val testService: TestService
) : ReactorTestServiceGrpc.TestServiceImplBase() {
    override fun getResult(request: Mono<GetResultRequest>): Mono<GetResultResponse> = request
        .flatMap {
            Mono.zip(testService.getResultFromServiceB(it.msg), testService.getResultFromServiceD(it.msg)) { resFromB, resFromD ->
                GetResultResponse.newBuilder()
                    .setRes("$resFromB, $resFromD")
                    .build()
            }
        }

    override fun getResultWithException(request: Mono<GetResultRequest>): Mono<GetResultResponse> = request
        .flatMap {
            testService.getResultExceptionFromServiceB(it.msg)
                .map { res ->
                    GetResultResponse.newBuilder()
                        .setRes(res)
                        .build()
                }
        }
}