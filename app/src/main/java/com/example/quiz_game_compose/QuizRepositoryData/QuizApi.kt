package com.example.quiz_game_compose.QuizRepositoryData

import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {
    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String
    ): QuizResponse
}