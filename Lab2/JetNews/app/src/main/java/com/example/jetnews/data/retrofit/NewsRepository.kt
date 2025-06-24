package com.example.jetnews.data.retrofit

import com.example.jetnews.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class NewsRepository {
    
    private val newsApiService = RetrofitClient.newsApiService
    
    suspend fun getEverything(
        apiKey: String,
        pageSize: Int? = null,
        page: Int? = null,
    ): Result<NewsApiResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = newsApiService.getEverything(
                    apiKey = apiKey,
                    pageSize = pageSize,
                    page = page,
                    q = "apple",
                    from = "2025-06-20",
                    to = "2025-06-21",
                    language = "es"
                )
                Result.Success(response)
            } catch (e: HttpException) {
                Result.Error(IOException("HTTP Error: ${e.code()} - ${e.message()}"))
            } catch (e: IOException) {
                Result.Error(e)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
} 