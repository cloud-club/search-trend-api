package com.cloudclub.searchtrend.client.google

import com.cloudclub.searchtrend.common.webclient.WebClientFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.validation.constraints.NotBlank

@Configuration
class GoogleClientConfig(
    private val googleClientProperties: GoogleClientProperties
) {
    @Bean
    fun googleClient(): GoogleClient {
        val webClient = WebClientFactory.generate(
            baseUrl = googleClientProperties.dailyTrendsUrl,
            connectionTimeoutMillis = 1500,
            readTimeoutMillis = 1500,
            writeTimeoutMillis = 1500
        )

        return ReactiveGoogleClient(webClient)
    }
}

@Configuration
@ConfigurationProperties(prefix = "client.search-trend.google")
@ConfigurationPropertiesBinding
data class GoogleClientProperties(
    @field:NotBlank
    var dailyTrendsUrl: String = ""
)
