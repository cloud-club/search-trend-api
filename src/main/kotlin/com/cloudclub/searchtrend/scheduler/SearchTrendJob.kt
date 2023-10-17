package com.cloudclub.searchtrend.scheduler

import com.cloudclub.searchtrend.client.google.GoogleClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class SearchTrendJob(
    private val googleClient: GoogleClient
) {
    private val logger = KotlinLogging.logger { }

    fun collectDailySearchTrendJob() {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                googleClient.getDailyTrends()
            }.onFailure { e ->
                logger.error("collectDailySearchTrendJob error", e)
            }

            logger.info { "collectDailySearchTrendJob finish" }
        }
    }

    fun collectRealTimeSearchTrendJob() {
        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                googleClient.getRealTimeTrends()
            }.onFailure { e ->
                logger.error("collectRealTimeSearchTrendJob error", e)
            }
            logger.info { "collectRealTimeSearchTrendJob finish" }
        }
    }
}
