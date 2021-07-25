package br.com.kotlin.adapter.out.feign.viacep.config

import feign.Response
import feign.RetryableException
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import java.lang.Exception

class Retry5xxErrorDecode : ErrorDecoder {

    private val defaultErrorDecoder: ErrorDecoder = ErrorDecoder.Default()

    override fun decode(s: String?, response: Response): Exception {
        val exception = defaultErrorDecoder.decode(s, response)
        return if(exception !is RetryableException && HttpStatus.valueOf(response.status()).is5xxServerError) {
            RetryableException(
                response.status(),
                response.status().toString(),
                response.request().httpMethod(),
                null,
                response.request()
            )
        } else exception
    }
}