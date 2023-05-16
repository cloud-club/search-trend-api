package com.cloudclub.searchtrend.presentation

import com.cloudclub.searchtrend.application.SearchTrendService
import com.cloudclub.searchtrend.common.extension.wrapOk
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Search Trend API")
@RestController
@RequestMapping("/api/v1/search-trends")
class SearchTrendResource(
    private val searchTrendService: SearchTrendService
) {
    @Operation(method = "Google Search Trend")
    @GetMapping("/google-trends")
    suspend fun getGoogleSearchTrend() = searchTrendService.getGoogleSearchTrend().wrapOk()
}
