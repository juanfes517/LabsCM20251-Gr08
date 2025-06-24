package com.example.jetnews.data.retrofit

import com.google.gson.annotations.SerializedName

data class NewsApiResponse(
    @SerializedName("status")
    val status: String,
    
    @SerializedName("totalResults")
    val totalResults: Int,
    
    @SerializedName("articles")
    val articles: List<NewsArticle>
)

data class NewsArticle(
    @SerializedName("source")
    val source: NewsSource,
    
    @SerializedName("author")
    val author: String?,
    
    @SerializedName("title")
    val title: String,
    
    @SerializedName("description")
    val description: String?,
    
    @SerializedName("url")
    val url: String,
    
    @SerializedName("urlToImage")
    val urlToImage: String?,
    
    @SerializedName("publishedAt")
    val publishedAt: String,
    
    @SerializedName("content")
    val content: String?
)

data class NewsSource(
    @SerializedName("id")
    val id: String?,
    
    @SerializedName("name")
    val name: String
) 