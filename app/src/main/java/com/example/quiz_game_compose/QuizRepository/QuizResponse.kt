package com.example.quiz_game_compose.QuizRepository

data class QuizResponse(
    val responseCode: Int,
    val results: List<Question>
)

data class Question(
    val type: String,
    val difficulty: String,
    val category: String,
    val question: String,
    val correctAnswer: String,
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
