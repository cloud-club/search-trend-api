package com.cloudclub.searchtrend.client.google

import com.cloudclub.searchtrend.client.google.model.GoogleDailySearchTrendModel
import com.cloudclub.searchtrend.client.google.model.GoogleRealTimeSearchTrendModel
import com.cloudclub.searchtrend.common.extension.mapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class SuspendableGoogleClient(
    private val webClient: WebClient
) : GoogleClient {
    private val logger = mu.KotlinLogging.logger {}

    override suspend fun getDailyTrends(): GoogleDailySearchTrendModel {
        return withContext(Dispatchers.IO) {
            webClient.get()
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
        }.trimIndent().substringAfter("\n").run {
            mapperGoogleResponseModel(this)
        }
    }

    override suspend fun getRealTimeTrends(): GoogleRealTimeSearchTrendModel {
        return withContext(Dispatchers.IO) {
            webClient.get()
                .uri("/trends/api/realtimetrends") { builder ->
                    builder
                        .queryParam("hl", "ko")
                        .queryParam("tz", -540)
                        .queryParam("cat", "all")
                        .queryParam("fi", 0)
                        .queryParam("fs", 0)
                        .queryParam("geo", "US")
                        .queryParam("ri", 300)
                        .queryParam("rs", 20)
                        .queryParam("sort", 0)
                        .build()
                }
                .retrieve()
                .awaitBody<String>()
        }.trimIndent().substringAfter("\n").run {
            mapperGoogleResponseModel(this)
        }
    }

    override suspend fun getReadTimeTrendsDetail(ids: Set<String>): GoogleRealTimeSearchTrendModel.StorySummary {
        return withContext(Dispatchers.IO) {
            webClient.get()
                .uri("/trends/api/stories/summary") { builder ->
                    builder
                        .queryParam("hl", "ko")
                        .queryParam("tz", -540)
                        .queryParam("cat", "all")
                        .queryParam("id", ids)
                        .build()
                }
                .retrieve()
                .awaitBody<String>()
        }.trimIndent().substringAfter("\n").run {
            mapperGoogleResponseModel(this)
        }
    }

    private inline fun <reified T> mapperGoogleResponseModel(response: String): T {
        return runCatching {
            mapper.readValue<T>(response)
        }.getOrElse { e ->
            logger.error { "fail to mapping / ${e.message}" }
            throw RuntimeException()
        }
    }
}
