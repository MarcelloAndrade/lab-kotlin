package br.com.kotlin.application.service

import br.com.kotlin.application.domain.Customer
import br.com.kotlin.application.port.`in`.CustomerUseCase
import br.com.kotlin.application.port.out.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService (
    private val customerRepository: CustomerRepository
) : CustomerUseCase {

    override fun execute(cep: String): Customer {
        return customerRepository.getByCep(cep)
    }
}