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
                Arguments.arguments("f3fa24e7-dd30-4fa9-8716-61faa1bc86e2"),
                Arguments.arguments("d0a7cbc3-b12d-43b9-8703-5631f3580575"),
                Arguments.arguments("edd0e3af-30a9-40f7-b451-9925522ef1d7"),
                Arguments.arguments("a857a72d-d708-4c41-ae27-91dddd759f03"),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("generateArgumentsTestsForGetCustomer")
    fun `Validate getCustomer when get customer ID` (customerId: String) {
        val dummyCustomerResponse = dummyObject<Customer>()
        whenever(customerRepository.getByCustomerId(customerId)).thenReturn(dummyCustomerResponse)

        val customer = customerService.execute(customerId)
        verify(customerRepository).getByCustomerId(customerId)
        assertEquals(customer.id, dummyCustomerResponse.id)
    }
}