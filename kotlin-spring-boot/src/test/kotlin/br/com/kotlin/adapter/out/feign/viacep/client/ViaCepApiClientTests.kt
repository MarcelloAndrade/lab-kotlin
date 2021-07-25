package br.com.kotlin.adapter.out.feign.viacep.client

import br.com.kotlin.adapter.out.feign.viacep.response.ViaCepResponse
import br.com.kotlin.helper.dummyObject
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import feign.RetryableException
import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import java.util.stream.Stream
import kotlin.test.assertEquals

@WebMvcTest
@ContextConfiguration(classes = [FeignAutoConfiguration::class])
@EnableFeignClients(clients = [ViaCepApiClient::class])
@Tag(value = "integration")
@TestPropertySource(properties = ["via-cep-api.url=http://localhost:8082"])
class ViaCepApiClientTests {

    private var server: WireMockServer = WireMockServer(WireMockConfiguration.wireMockConfig().port(8082))

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var viaCepApiClient: ViaCepApiClient

    @BeforeEach
    fun start(){
        server.start()
    }

    @AfterEach
    fun stop(){
        server.stop()
    }

    companion object {
        @JvmStatic
        fun generateArgumentsTestsForGetViaCep(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("06020194"),
                Arguments.arguments("01001000")
            )
        }
    }

    @ParameterizedTest
    @MethodSource("generateArgumentsTestsForGetViaCep")
    fun `Validate getViaCep when cep exists`(cep: String) {
        val dummyViaCepResponse = dummyObject<ViaCepResponse>()

        server.stubFor(
            WireMock.get("/$cep/json")
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.OK.value())
                        .withBody(objectMapper.writeValueAsString(dummyViaCepResponse))
                )
        )

        val viaCep = viaCepApiClient.getCep(cep)
        assertEquals(viaCep.cep, dummyViaCepResponse.cep)
    }

    @Test
    fun `Validate getViaCep when receive 500(Internal server error)`() {
        val cep = dummyObject<String>()
        server.stubFor(
            WireMock.get("/$cep/json")
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                )
        )

        assertThrows<RetryableException> {
            viaCepApiClient.getCep(cep)
        }
    }
}