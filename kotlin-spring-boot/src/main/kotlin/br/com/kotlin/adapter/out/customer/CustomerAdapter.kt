package br.com.kotlin.adapter.out.customer

import br.com.kotlin.adapter.out.customer.converter.toDomain
import br.com.kotlin.adapter.out.feign.viacep.client.ViaCepApiClient
import br.com.kotlin.application.domain.Customer
import br.com.kotlin.application.port.out.CustomerRepository
import feign.FeignException
import org.springframework.stereotype.Service
import org.webjars.NotFoundException
import java.lang.Exception
import java.time.LocalDateTime

@Service
class CustomerAdapter(
    private val viaCepApiClient: ViaCepApiClient
) : CustomerRepository {

    override fun getByCep(cep: String): Customer {
        try {
            val adress = viaCepApiClient.getCep(cep).toDomain()
            return Customer("8650bc48-a1d4-4b4c-bbba-fd1b23c541cf", "Marcello Andrade", 32, LocalDateTime.now(), adress)
        } catch (ex: FeignException) {
            when(ex.status()) {
                404 -> throw NotFoundException("Customer getByCep $cep - NotFoundException")
                in 400..499 -> {
                    throw Exception("400..499 $ex")
                }
                else -> throw ex
            }
        }
    }
}