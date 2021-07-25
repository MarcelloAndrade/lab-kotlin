package br.com.kotlin.adapter.out.feign.viacep.config

import feign.Retryer
import feign.codec.ErrorDecoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignClientConfig (
    @Value("\${feign.default.period:100}") private val period: Long,
    @Value("\${feign.default.maxPeriod:300}") private val maxPeriod: Long,
    @Value("\${feign.default.maxAttempts:3}") private val maxAttempts: Int
) {

    @Bean
    fun retryver(): Retryer {
        return Retryer.Default(period,maxPeriod, maxAttempts)
    }

    @Bean
    fun errorDecode(): ErrorDecoder {
        return Retry5xxErrorDecode()
    }
}