package br.com.kotlin.adapter.out.customer.converter

import br.com.kotlin.adapter.out.feign.viacep.response.ViaCepResponse
import br.com.kotlin.application.domain.Adress

fun ViaCepResponse.toDomain() =
    Adress(
        cep = cep,
        logradouro = logradouro,
        complemento = complemento,
        bairro = bairro,
        uf = uf
    )