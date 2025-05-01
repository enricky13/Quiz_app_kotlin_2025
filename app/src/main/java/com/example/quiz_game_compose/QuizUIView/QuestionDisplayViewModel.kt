package com.example.quiz_game_compose.QuizUIView

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz_game_compose.QuizRepository.QuizApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuestionDisplayViewModel(
    private val quizApi: QuizApi
): ViewModel() {
    private val _uiState = MutableStateFlow<QuizUiState>(QuizUiState.Loading)
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()
    private val FINDME = "ENRCRZ"

    init {
        retrieveQuestions(10, 9, "easy", "multiple")
    }

    private fun retrieveQuestions(amount: Int, category: Int, difficulty: String, type: String){
        /**
         * Instead of writing how to get back data from a retrofit service here, look at the following lines in the
         * uncommented code and see how you are connecting the viewmodel UIState to a success or error response
         */
//        QuizRetrofitClient.instance.getQuestions(amount,category,difficulty,type).enqueue( object : retrofit2.Callback<QuizResponse> {
//            override fun onResponse(p0: Call<QuizResponse>, p1: Response<QuizResponse>) {
//            }
//
//            override fun onFailure(p0: Call<QuizResponse>, p1: Throwable) {
//            }
//        })

        viewModelScope.launch {
            try {
                val response = quizApi.getQuestions(amount, category, difficulty, type)
                _uiState.value = QuizUiState.Success(response)
                Log.d(FINDME, "Response: ${response.results}")
            }
            catch (e: Exception){
                _uiState.value = QuizUiState.Error(e.localizedMessage ?: "Unknown Error")
                Log.d(FINDME, "Exception: ${e.message ?: "Unknown error"}")
            }
        }
    }
}