package br.com.kotlin.adapter.`in`.controller

import br.com.kotlin.application.domain.Customer
import br.com.kotlin.application.port.`in`.CustomerUseCase
import br.com.kotlin.config.property.HeadersCost
import br.com.kotlin.helper.dummyObject
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import java.util.stream.Stream
import kotlin.test.assertEquals

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
internal class CustomerControllerTests {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var customerUseCase: CustomerUseCase

    companion object {
        @JvmStatic
        fun generateArgumentsTestsForGetCustomer(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("06020194"),
                Arguments.arguments("01001-000"),
                Arguments.arguments("19900000"),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("generateArgumentsTestsForGetCustomer")
    fun `Validate getAdressCustomerByCep when get CEP customer` (cep: String) {
        val dummyCustomerResponse = dummyObject<Customer>()
        whenever(customerUseCase.execute(cep)).thenReturn(dummyCustomerResponse)

        val resultActions = mockMvc.perform(
            get("/v1/adress/$cep/customer")
                .header(HeadersCost.HEADER_CLIENT_ID, HeadersCost.VALEU_CLIENT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo{response -> response.response}
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))

        val customerResponse: Customer =
            objectMapper.readValue(
                resultActions.andReturn().response.contentAsString,
                Customer::class.java
            )

        verify(customerUseCase).execute(cep)
        assertEquals(dummyCustomerResponse.id, customerResponse.id)
    }
}