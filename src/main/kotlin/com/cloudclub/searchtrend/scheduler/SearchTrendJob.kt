package com.cloudclub.searchtrend.scheduler

import com.cloudclub.searchtrend.client.google.GoogleClient
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class SearchTrendJob(
    private val googleClient: GoogleClient
) {
    private val logger = KotlinLogging.logger { }

    fun collectDailySearchTrendJob() {
        runBlocking {
            googleClient.getDailyTrends()
        }
        logger.info { "collectDailySearchTrendJob finish" }
    }

    fun collectRealTimeSearchTrendJob() {
        runBlocking {
            googleClient.getRealTimeTrends()
        }
        logger.info { "collectRealTimeSearchTrendJob finish" }
    }
}
