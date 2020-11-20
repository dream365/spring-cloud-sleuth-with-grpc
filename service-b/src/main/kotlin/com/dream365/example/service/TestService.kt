package com.dream365.example.service

import com.dream365.example.GetResultRequest
import com.dream365.example.GetResultResponse
import com.dream365.example.ReactorTestServiceGrpc
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class TestService(
    private val serviceCStub: ReactorTestServiceGrpc.ReactorTestServiceStub
) {
    fun getResultFromServiceC(requestMsg: String): Mono<GetResultResponse> = serviceCStub.getResult(buildRequest("$requestMsg->B"))

    private fun buildRequest(requestMsg: String) = GetResultRequest
        .newBuilder()
        .setMsg(requestMsg)
        .build()
}