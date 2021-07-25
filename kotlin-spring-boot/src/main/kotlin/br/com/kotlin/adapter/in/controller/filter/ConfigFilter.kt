package br.com.kotlin.adapter.`in`.controller.filter

import br.com.kotlin.config.property.HeadersCost
import org.slf4j.MDC
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class ConfigFilter : Filter {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain) {
        val httpRequest: HttpServletRequest = request as HttpServletRequest
        MDC.put(HeadersCost.HEADER_CLIENT_ID, httpRequest.getHeader(HeadersCost.HEADER_CLIENT_ID))
        chain.doFilter(request, response)
    }
}