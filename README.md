# Search Trend Api

> 검색 도메인의 인기 검색어를 실시간 파싱

### Spec

- springboot
- webflux with coroutine
- kotlin

### Swagger

> http://localhost:8080/swagger-ui/index.html

### Google Client

```kotlin
interface GoogleClient {
    /** 일간 검색 트랜드 */
    suspend fun getDailyTrends(): GoogleDailySearchTrendModel

    /** 실시간 검색 트랜드 */
    suspend fun getRealTimeTrends(): GoogleRealTimeSearchTrendModel

    /** 실시간 검색 트랜드 상세 정보 */
    suspend fun getReadTimeTrendsDetail(ids: Set<String>): GoogleRealTimeSearchTrendModel.StorySummary
}
```

### Reference 

- 아파치 카프카 book
