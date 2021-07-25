package br.com.kotlin.application.port.out

import br.com.kotlin.application.domain.Customer

interface CustomerRepository {

    fun getByCep(cep: String): Customer
}