package com.dream365.example.service

import com.dream365.example.GetResultRequest
import com.dream365.example.ReactorTestServiceGrpc
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class TestService(
    private val serviceBStub: ReactorTestServiceGrpc.ReactorTestServiceStub,
    private val serviceDStub: ReactorTestServiceGrpc.ReactorTestServiceStub
) {
    fun getResultFromServiceB(requestMsg: String): Mono<String> = serviceBStub
        .getResult(buildRequest("$requestMsg->A"))
        .map { it.res }

    fun getResultFromServiceD(requestMsg: String): Mono<String> = serviceDStub
        .getResult(buildRequest("$requestMsg->A"))
        .map { it.res }

    fun getResultExceptionFromServiceB(requestMsg: String): Mono<String> = serviceBStub
        .getResultWithException(buildRequest("$requestMsg->A"))
        .map { it.res }

    private fun buildRequest(requestMsg: String) = GetResultRequest
        .newBuilder()
        .setMsg(requestMsg)
        .build()
}