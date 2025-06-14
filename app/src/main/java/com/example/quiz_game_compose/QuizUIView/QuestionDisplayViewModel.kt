package com.example.quiz_game_compose.QuizUIView

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz_game_compose.QuizRepository.QuizApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.quiz_game_compose.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * HiltViewModel notifies the system that this is using Dependancy Injection
 */
@HiltViewModel
class QuestionDisplayViewModel @Inject constructor(
    private val quizApi: QuizApi
): ViewModel() {
    /**
     *  Whenever we recreate this class we must remember that we are setting the default UIState to laoding, if we
     *  don't pass the viewmodel state from the startscreen to the displayScreen, we can run into a
     *  race condition and possibly show the loading even after we retrieved something
     */
    private val _uiState = MutableStateFlow<QuizUiState>(QuizUiState.Loading)
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    /**
     * Observed in QuestionDisplay Screen, once it's true, easy navigation to the display screen
     */
    private val _shouldDisplayResults = MutableStateFlow(false)
    val shouldDisplayResults = _shouldDisplayResults.asStateFlow()

    /**
     * Must make a variable a stateflow if you want to update it and have a composable view updated
     */
    var currentQuestionIndex by mutableIntStateOf(0) // 0 - 9 limit
    var correctAnswersCheck = 0
    private var incorrectAnswersCheck = 0
    private val FINDME = "FINDME"

    fun retrieveQuestions(amount: Int, category: String, difficulty: String, type: String) {
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
        Log.d("FINDME", "amount: $amount, category: $category, difficulty: $difficulty, type: $type")
        viewModelScope.launch {
            try {
                // You are now doing this in the background
                val response = withContext(Dispatchers.IO){
                    quizApi.getQuestions(amount, getCategory(category), difficulty.lowercase(), type)
                }
                _uiState.value = QuizUiState.Success(response)
                Log.d(FINDME, "Response: ${response.results} Response Code:${response.responseCode}")
            }
            catch (e: Exception){
                _uiState.value = QuizUiState.Error(e.localizedMessage ?: "Unknown Error")
                Log.d(FINDME, "Exception: ${e.message ?: "Unknown error"}")
            }
        }
    }

    // Should recalculate this somewhere else?
    private fun getCategory(category: String): Int{
        return when(category){
            "General Knowledge" -> { 9 }
            "Entertainment: Film" -> { 11 }
            "Entertainment: Video Games" -> { 15 }
            else -> {20}
        }
    }

    fun getShuffledAnswers(correctAnswer: String, incorrectAnswers: List<String>): List<String> {
        /**
         * If you do (correctAnswer + incorrectAnswer) you will have the incorrectAnswers added to the string
         * and get back 1 string with all the answers.
         */
        return (incorrectAnswers + correctAnswer).shuffled()
    }

    fun answerOnClick(answer: String): () -> Unit {
        return {
            if (answer == (uiState.value as QuizUiState.Success).data.results[currentQuestionIndex].correctAnswer) {
                Log.d(FINDME, "Correct answer: $answer")
                correctAnswersCheck++
            }
            else{
                Log.d(FINDME, "Incorrect Answer: $answer")
                incorrectAnswersCheck++
            }
            if ((uiState.value as QuizUiState.Success).data.results.size - 1 > currentQuestionIndex){
                currentQuestionIndex++
            }
            else {
                _shouldDisplayResults.value = true
            }
        }
    }
}