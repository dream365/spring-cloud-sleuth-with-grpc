package com.dream365.example.grpc

import com.dream365.example.ReactorTestServiceGrpc
import com.dream365.example.getResultRequest
import com.dream365.example.getResultResponse
import org.lognet.springboot.grpc.GRpcService
import reactor.core.publisher.Mono

@GRpcService
class GprcTestService : ReactorTestServiceGrpc.TestServiceImplBase() {
    override fun getResult(reactorRequest: Mono<getResultRequest>): Mono<getResultResponse> {
        TODO()
    }
}