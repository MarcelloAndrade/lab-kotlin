package br.com.kotlin.application.port.`in`

import br.com.kotlin.application.domain.Customer

interface CustomerUseCase {

    fun execute(id: String): Customer

}