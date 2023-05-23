package com.cloudclub.searchtrend.client.google

import com.cloudclub.searchtrend.client.google.model.GoogleDailySearchTrendModel
import com.cloudclub.searchtrend.client.google.model.GoogleRealTimeSearchTrendModel

interface GoogleClient {
    /** 일간 검색 트랜드 */
    suspend fun getDailyTrends(): GoogleDailySearchTrendModel

    /** 실시간 검색 트랜드 */
    suspend fun getRealTimeTrends(): GoogleRealTimeSearchTrendModel

    /** 실시간 검색 트랜드 상세 정보 */
    suspend fun getReadTimeTrendsDetail(ids: Set<String>): GoogleRealTimeSearchTrendModel.StorySummary
}
