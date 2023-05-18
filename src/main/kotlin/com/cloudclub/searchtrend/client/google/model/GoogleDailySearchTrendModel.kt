package com.cloudclub.searchtrend.client.google.model

data class GoogleDailySearchTrendModel(
    val default: DefaultResponse
)

data class DefaultResponse(
    val trendingSearchesDays: List<TrendingSearchDayInfo>,
    val endDateForNextRequest: String,
    val rssFeedPageUrl: String
)

data class TrendingSearchDayInfo(
    val date: String,
    val formattedDate: String,
    val trendingSearches: List<TrendingSearchInfo>
)

data class TrendingSearchInfo(
    val title: Title,
    val formattedTraffic: String,
    val relatedQueries: List<RelatedQueries>,
    val image: Image,
    val articles: List<Article>,
    val shareUrl: String
) {
    data class Title(
        val query: String,
        val exploreLink: String
    )

    data class RelatedQueries(
        val query: String,
        val exploreLink: String
    )

    data class Article(
        val title: String,
        val timeAgo: String,
        val source: String,
        val image: Image?,
        val url: String,
        val snippet: String
    )

    data class Image(
        val newsUrl: String,
        val source: String,
        val imageUrl: String
    )

    data class StorySummary(
        val featuredStories: List<Any>,
        val trendingStories: List<TrendingStory>
    )

    data class TrendingStory(
        val image: Image,
        val shareUrl: String,
        val articles: List<Article>,
        val idsForDedup: List<String>,
        val id: String,
        val title: String,
        val entityNames: List<String>
    )
}
