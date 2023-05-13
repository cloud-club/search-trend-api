package com.cloudclub.searchtrend.client.google

interface GoogleClient {
    suspend fun getDailyTrends(): String
}
