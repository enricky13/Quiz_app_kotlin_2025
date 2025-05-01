package com.example.quiz_game_compose.QuizUIView

import com.example.quiz_game_compose.QuizRepository.QuizResponse

sealed class QuizUiState {
    object Loading: QuizUiState()
    data class Success(val data: QuizResponse): QuizUiState()
    data class Error(val message: String): QuizUiState()
}