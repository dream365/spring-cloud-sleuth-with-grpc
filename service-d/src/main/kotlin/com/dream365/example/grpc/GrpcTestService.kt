package com.dream365.example.grpc

import com.dream365.example.ReactorTestServiceGrpc
import com.dream365.example.GetResultRequest
import com.dream365.example.GetResultResponse
import org.lognet.springboot.grpc.GRpcService
import reactor.core.publisher.Mono

@GRpcService
class GrpcTestService : ReactorTestServiceGrpc.TestServiceImplBase() {
    override fun getResult(request: Mono<GetResultRequest>): Mono<GetResultResponse> = request
        .map {
            buildResponse("${it.msg}->D")
        }

    private fun buildResponse(responseMsg: String) = GetResultResponse
        .newBuilder()
        .setRes(responseMsg)
        .build()
}