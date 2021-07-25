package br.com.kotlin.adapter.out.feign.viacep.client

import br.com.kotlin.adapter.out.feign.viacep.config.FeignClientConfig
import br.com.kotlin.adapter.out.feign.viacep.response.ViaCepResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import javax.websocket.server.PathParam

@FeignClient(
    name = "ViaCepApiClient",
    url = "\${via-cep-api.url}",
    configuration = [FeignClientConfig::class]
)
interface ViaCepApiClient {

    @GetMapping("/{cep}/json")
    fun getCep(@PathVariable("cep") cep: String): ViaCepResponse
}