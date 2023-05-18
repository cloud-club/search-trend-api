package com.cloudclub.searchtrend.application

import com.cloudclub.searchtrend.client.google.GoogleClient
import org.springframework.stereotype.Service

@Service
class SearchTrendService(
    private val googleClient: GoogleClient
) {
    suspend fun getGoogleDailySearchTrend() = googleClient.getDailyTrends()

    suspend fun getGoogleRealTimeSearchTrend() = googleClient.getRealTimeTrends()

    suspend fun getGoogleRealTimeSearchTrendDetail(ids: Set<String>) = googleClient.getReadTimeTrendsDetail(ids)
}
