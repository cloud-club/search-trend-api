package com.cloudclub.searchtrend.application

import com.cloudclub.searchtrend.client.google.GoogleClient
import org.springframework.stereotype.Service

@Service
class GoogleSearchTrendService(
    private val googleClient: GoogleClient
) {
    suspend fun getSearchTrend() = googleClient.getDailyTrends()
}
