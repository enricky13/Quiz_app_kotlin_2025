package com.example.quiz_game_compose.QuizRepositoryData
import javax.inject.Inject

class QuizRepository @Inject constructor(
    private val api: QuizApi
) {
    suspend fun fetchQuiz(amount: Int, category: Int, difficulty: String, type: String): QuizResponse {
        return api.getQuestions(amount, category, difficulty, type)
    }
}