package com.example.jetnews.data.posts.impl

import android.content.Context
import android.util.Log
import com.example.jetnews.R
import com.example.jetnews.data.Result
import com.example.jetnews.data.posts.PostsRepository
import com.example.jetnews.data.retrofit.NewsApiResponse
import com.example.jetnews.data.retrofit.NewsArticle
import com.example.jetnews.data.retrofit.NewsRepository
import com.example.jetnews.data.workers.PostsWorker
import com.example.jetnews.model.Post
import com.example.jetnews.model.PostsFeed
import com.example.jetnews.model.Publication
import com.example.jetnews.model.Metadata
import com.example.jetnews.model.ParagraphType
import com.example.jetnews.model.Paragraph
import com.example.jetnews.model.PostAuthor
import com.example.jetnews.utils.addOrRemove
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import java.util.UUID
import kotlin.random.Random
import androidx.work.WorkManager
import androidx.work.OneTimeWorkRequestBuilder
import com.google.gson.Gson
import android.widget.Toast
import kotlinx.coroutines.delay

class RetrofitPostRepository(private val context: Context) : PostsRepository {

    private val newsRepository = NewsRepository()
    private val favorites = MutableStateFlow<Set<String>>(setOf())
    private val postsFeed = MutableStateFlow<PostsFeed?>(null)

    override suspend fun getPost(postId: String?): Result<Post> {
        return withContext(Dispatchers.IO) {
            val post = posts.allPosts.find { it.id == postId }

            if (post == null) {
                Result.Error(IllegalArgumentException("Post not found"))
            } else {
                Result.Success(post)
            }
        }
    }

    override suspend fun getPostsFeed(): Result<PostsFeed> {
        return withContext(Dispatchers.IO) {
            // Lanzar el worker (no esperamos su resultado)
            val workManager = WorkManager.getInstance(context)
            val workRequest = OneTimeWorkRequestBuilder<PostsWorker>().build()
            workManager.enqueue(workRequest)

            // Leer los datos más recientes de SharedPreferences
            val prefs = context.getSharedPreferences(PostsWorker.PREF_NAME, Context.MODE_PRIVATE)
            val postsFeedJson = prefs.getString(PostsWorker.PREF_POSTS_FEED, null)
            val gson = Gson()

            val postsFeedResult = if (postsFeedJson != null) {
                gson.fromJson(postsFeedJson, PostsFeed::class.java)
            } else {
                null
            }

            if (postsFeedResult != null) {
                postsFeed.update { postsFeedResult }
                Result.Success(postsFeedResult)
            } else {
                Result.Error(Exception("Los datos aún se están obteniendo."))
            }
        }
    }

    override fun observeFavorites(): Flow<Set<String>> = favorites
    override fun observePostsFeed(): Flow<PostsFeed?> = postsFeed

    override suspend fun toggleFavorite(postId: String) {
        favorites.update {
            it.addOrRemove(postId)
        }
    }

    private var requestCount = 0

    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0
}