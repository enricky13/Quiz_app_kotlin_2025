package com.example.quiz_game_compose.QuizUIView

import com.example.quiz_game_compose.QuizRepository.QuizResponse

/**
 * Saves and holds a reference to the state of the UI, by default it is Loading
 * Once conditions are met, we can make this a Success, or Error State, this will be
 * observable on the compose screen.
 */
sealed class QuizUiState {
    object Loading: QuizUiState()
    data class Success(val data: QuizResponse): QuizUiState()
    data class Error(val message: String): QuizUiState()
}