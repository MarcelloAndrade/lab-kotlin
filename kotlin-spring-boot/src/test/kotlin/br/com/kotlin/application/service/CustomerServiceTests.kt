package br.com.kotlin.application.service

import br.com.kotlin.application.domain.Customer
import br.com.kotlin.application.port.out.CustomerRepository
import br.com.kotlin.helper.dummyObject
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

internal class CustomerServiceTests {

    private val customerRepository: CustomerRepository = mock()

    private val customerService = CustomerService(customerRepository)

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
        whenever(customerRepository.getByCep(cep)).thenReturn(dummyCustomerResponse)

        val customer = customerService.execute(cep)
        verify(customerRepository).getByCep(cep)
        assertEquals(customer.id, dummyCustomerResponse.id)
    }
}