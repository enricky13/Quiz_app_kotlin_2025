package com.example.quiz_game_compose.QuizRepositoryData

import com.google.gson.annotations.SerializedName

data class QuizResponse(
    val responseCode: Int,
    val results: List<Question>
)

data class Question(
    val type: String,
    val difficulty: String,
    val category: String,
    val question: String,
    @SerializedName("correct_answer")
    val correctAnswer: String,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String>
)

/**
 *
 * How the JSON looks
 * "results": [
 *     {
 *       "type": "multiple",
 *       "difficulty": "easy",
 *       "category": "General Knowledge",
 *       "question": "What kind of aircraft was developed by Igor Sikorsky in the United States in 1942?",
 *       "correct_answer": "Helicopter",
 *       "incorrect_answers": [
 *         "Stealth Blimp",
 *         "Jet",
 *         "Space Capsule"
 *       ]
 *     }
 */
