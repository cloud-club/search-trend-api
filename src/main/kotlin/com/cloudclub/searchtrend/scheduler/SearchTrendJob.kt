package com.cloudclub.searchtrend.scheduler

import com.cloudclub.searchtrend.client.google.GoogleClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class SearchTrendJob(
    private val googleClient: GoogleClient
) {
    private val logger = KotlinLogging.logger { }

    suspend fun collectDailySearchTrendJob() {
        withContext(Dispatchers.IO) {
            googleClient.getDailyTrends()
        }

        logger.info { "collectDailySearchTrendJob finish" }
    }

    suspend fun collectRealTimeSearchTrendJob() {
        withContext(Dispatchers.IO) {
            googleClient.getRealTimeTrends()
        }
        logger.info { "collectRealTimeSearchTrendJob finish" }
    }
}
