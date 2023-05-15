package com.cloudclub.searchtrend.application

import com.cloudclub.searchtrend.client.google.GoogleClient
import com.cloudclub.searchtrend.client.google.GoogleSearchTrendModel
import com.cloudclub.searchtrend.common.extension.mapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Service

@Service
class GoogleSearchTrendService(
    private val googleClient: GoogleClient
) {
    suspend fun getSearchTrend(): GoogleSearchTrendModel {
        val googleTrendsResponse = googleClient.getDailyTrends()
            .trimIndent()
            .substringAfter("\n")

        return runCatching {
            mapper.readValue<GoogleSearchTrendModel>(googleTrendsResponse)
        }.getOrElse {
            throw RuntimeException("fail to get Google Trend Search Response")
        }
    }
}
