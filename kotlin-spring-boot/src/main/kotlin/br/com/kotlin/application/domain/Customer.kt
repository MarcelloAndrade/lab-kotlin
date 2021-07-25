package br.com.kotlin.application.domain

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Customer(
    val id: String,
    val name: String,
    val age: Int,
    val timestamp: LocalDateTime,
    val adress: Adress
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Adress(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val uf: String
)