package br.com.kotlin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/customer")
class CustomerController {

    @GetMapping
    fun list(): List<String> {
        return Arrays.asList("Marcello", "Jessica");
    }
}