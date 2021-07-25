package br.com.kotlin.adapter.`in`.controller

import br.com.kotlin.application.domain.Customer
import br.com.kotlin.application.port.`in`.CustomerUseCase
import br.com.kotlin.config.property.HeadersCost
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/customer")
class CustomerController {

    @Autowired
    lateinit var customerUseCase: CustomerUseCase

    @GetMapping("/v1/{id}")
    fun getCustomerById(
        @PathVariable("id") id: String,
        @RequestHeader(HeadersCost.HEADER_CLIENT_ID) xClientId: String
    ): ResponseEntity<Customer> {
        val response = customerUseCase.execute(id)
        return ResponseEntity(response, HttpStatus.OK)
    }
}