package com.cloudclub.searchtrend.presentation

import com.cloudclub.searchtrend.application.SearchTrendService
import com.cloudclub.searchtrend.common.extension.wrapOk
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Search Trend API")
@RestController
@RequestMapping("/api/v1/search-trends")
class SearchTrendResource(
    private val searchTrendService: SearchTrendService
) {
    @Operation(method = "Google Daily Search Trend")
    @GetMapping("/google/daily-trends")
    suspend fun getGoogleDailySearchTrend() = searchTrendService.getGoogleDailySearchTrend().wrapOk()

    @Operation(method = "Google Real-Time Search Trend")
    @GetMapping("/google/real-time-trends")
    suspend fun getGoogleRealTimeSearchTrend() = searchTrendService.getGoogleRealTimeSearchTrend().wrapOk()

    @Operation(method = "Google Real-Time Search Trend Detail")
    @GetMapping("/google/read-time-trends-detail")
    suspend fun getGoogleRealTimeSearchTrendDetail(
        @RequestParam(name = "id") ids: Set<String>
    ) = searchTrendService.getGoogleRealTimeSearchTrendDetail(ids).wrapOk()
}
