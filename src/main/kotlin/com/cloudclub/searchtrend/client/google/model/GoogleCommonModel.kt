package com.cloudclub.searchtrend.client.google.model

import java.awt.Image

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
