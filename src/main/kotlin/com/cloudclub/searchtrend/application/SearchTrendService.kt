package com.cloudclub.searchtrend.application

import com.cloudclub.searchtrend.client.google.GoogleClient
import org.springframework.stereotype.Service

@Service
class SearchTrendService(
    private val googleClient: GoogleClient
) {
    suspend fun getGoogleSearchTrend() = googleClient.getDailyTrends()
}
