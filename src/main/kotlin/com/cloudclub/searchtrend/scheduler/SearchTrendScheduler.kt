package com.cloudclub.searchtrend.scheduler

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SearchTrendScheduler(
    private val schedulerJob: SearchTrendJob
) {
    @Scheduled(cron = "0 0/30 * * * *")
    suspend fun runCollectDailySearchTrendJob() {
        schedulerJob.collectDailySearchTrendJob()
    }

    @Scheduled(cron = "0 0/15 * * * *")
    suspend fun runCollectRealTimeSearchTrendJob() {
        schedulerJob.collectRealTimeSearchTrendJob()
    }
}
