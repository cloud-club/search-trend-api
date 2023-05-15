package com.cloudclub.searchtrend.client.google

import com.cloudclub.searchtrend.common.extension.mapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class ReactiveGoogleClient(
    private val webClient: WebClient
) : GoogleClient {
    override suspend fun getDailyTrends(): GoogleSearchTrendModel {
        val response = webClient.get()
            .uri("/trends/api/dailytrends") { builder ->
                builder
                    .queryParam("hl", "ko")
                    .queryParam("tz", -540)
                    .queryParam("geo", "KR")
                    .queryParam("hl", "ko")
                    .queryParam("ns", 15)
                    .queryParam("ed", 20230507) // 가변
                    .build()
            }
            .retrieve()
            .awaitBody<String>()
            .trimIndent()
            .substringAfter("\n")
        
        return runCatching {
            mapper.readValue<GoogleSearchTrendModel>(response)
        }.getOrElse {
            throw RuntimeException("fail to get Google Trend Search Response")
        }
    }
}
