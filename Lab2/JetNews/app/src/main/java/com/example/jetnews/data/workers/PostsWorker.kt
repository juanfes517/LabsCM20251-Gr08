package com.example.jetnews.data.workers

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.jetnews.R
import com.example.jetnews.data.posts.impl.posts
import com.example.jetnews.data.retrofit.NewsApiResponse
import com.example.jetnews.data.retrofit.NewsArticle
import com.example.jetnews.data.retrofit.NewsRepository
import com.example.jetnews.model.Metadata
import com.example.jetnews.model.Paragraph
import com.example.jetnews.model.ParagraphType
import com.example.jetnews.model.Post
import com.example.jetnews.model.PostAuthor
import com.example.jetnews.model.PostsFeed
import com.example.jetnews.model.Publication
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import java.util.UUID
import kotlin.random.Random
import androidx.core.content.edit

private const val TAG = "PostWorker"

class PostsWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    companion object {
        const val KEY_POSTS_FEED_JSON = "KEY_POSTS_FEED_JSON"
        const val PREF_NAME = "posts_worker_prefs"
        const val PREF_POSTS_FEED = "posts_feed_json"
        const val PREF_LAST_UPDATE = "last_update"
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override suspend fun doWork(): Result {

        return withContext(Dispatchers.IO) {
            delay(3000)

            return@withContext try {

                val newsRepository = NewsRepository()
                val page = Random.nextInt(1, 9)
                val resultApiResponse: com.example.jetnews.data.Result<NewsApiResponse> =
                    newsRepository.getEverything("827a145b62a1489fbb07280fbe02bf41", 11, page)

                when (resultApiResponse) {
                    is com.example.jetnews.data.Result.Success -> {
                        val newsData: NewsApiResponse = resultApiResponse.data
                        val articles: List<NewsArticle> = newsData.articles

                        if (articles.isEmpty()) {
                            throw Exception("News not found")
                        }

                        val postsList: MutableList<Post> = mutableListOf()

                        for (article in articles) {
                            postsList.add(
                                Post(
                                    id = UUID.randomUUID().mostSignificantBits.toString(),
                                    title = article.title,
                                    subtitle = article.description,
                                    url = article.url,
                                    publication = Publication(
                                        name = article.source.name,
                                        logoUrl = article.url,
                                    ),
                                    metadata = Metadata(
                                        author = PostAuthor(
                                            name = article.author ?: "Unknown",
                                        ),
                                        date = article.publishedAt,
                                        readTimeMinutes = Random.nextInt(1, 10),
                                    ),
                                    paragraphs = listOf(
                                        Paragraph(
                                            type = ParagraphType.Text,
                                            text = article.content ?: article.description ?: "Sin contenido disponible",
                                        ),
                                    ),
                                    imageId = R.drawable.post_5,
                                    imageThumbId = article.urlToImage.toString(),
                                ),
                            )
                        }

                        val newPostsFeed = PostsFeed(
                            highlightedPost = if (postsList.size > 5) postsList[5] else posts.highlightedPost,
                            recommendedPosts = postsList,
                            popularPosts = if (postsList.size >= 3) listOf(postsList[0], postsList[1], postsList[2]) else postsList.take(3),
                            recentPosts = listOf(),
                        )

                        // Save to SharedPreferences instead of WorkData
                        val gson = Gson()
                        val postsFeedJson = gson.toJson(newPostsFeed)
                        val prefs = applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

                        prefs.edit() {
                            putString(PREF_POSTS_FEED, postsFeedJson)
                                .putLong(PREF_LAST_UPDATE, System.currentTimeMillis())
                        }

                        // Return success with minimal data
                        val outputData = workDataOf("success" to true)
                        Result.success(outputData)
                    }
                    is com.example.jetnews.data.Result.Error -> {
                        Result.failure()
                    }
                }

            } catch (e: Exception) {
                Log.e(TAG, "Error loading news", e)
                Result.failure()
            }
        }
    }
}