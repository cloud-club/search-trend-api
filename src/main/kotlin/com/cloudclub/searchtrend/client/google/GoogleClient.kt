package com.cloudclub.searchtrend.client.google

import com.cloudclub.searchtrend.client.google.model.GoogleDailySearchTrendModel
import com.cloudclub.searchtrend.client.google.model.GoogleRealTimeSearchTrendModel

interface GoogleClient {
    suspend fun getDailyTrends(): GoogleDailySearchTrendModel

    suspend fun getRealTimeTrends(): GoogleRealTimeSearchTrendModel
}
