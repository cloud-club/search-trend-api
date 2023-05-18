package com.cloudclub.searchtrend.client.google.model

data class GoogleRealTimeSearchTrendModel(
    val featuredStoryIds: List<String>,
    val trendingStoryIds: List<String>,
    val storySummaries: Any,
    val date: String,
    val hideAllImages: Boolean
) {
    data class StorySummary(
        val featuredStories: Map<String, Any>,
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
