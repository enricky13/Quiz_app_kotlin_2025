package com.example.quiz_game_compose.ComposableScreens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quiz_game_compose.QuizUIView.QuestionDisplayViewModel
import com.example.quiz_game_compose.QuizUIView.QuizUiState

private val FINDME = "FINDME"
@Composable
fun QuestionScreen(navController: NavController, viewModel: QuestionDisplayViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsState()
    val shouldNavigate by viewModel.shouldDisplayResults.collectAsState()
    Log.d(FINDME, "UIState: $uiState")
    Column {
        when (uiState) {
            is QuizUiState.Loading -> {
                Log.d(FINDME, "Loading Compose")
            }
            is QuizUiState.Success -> {
                val response = (uiState as QuizUiState.Success).data.results
                Log.d(FINDME, "Success Compose: $response")
                val currentQuestion = response[viewModel.currentQuestionIndex]
                DisplayQuestion(currentQuestion.question)
                DisplayAnswers(
                    viewModel.getShuffledAnswers(
                        currentQuestion.correctAnswer,
                        currentQuestion.incorrectAnswers
                    ),
                    viewModel
                )
                /**
                 * LaunchedEffect checks the variable (key) to see if it changes then proceed with the
                 * logic inside the lambda just once. Prevents multiple accidental triggers
                 */
                LaunchedEffect(shouldNavigate) {
                    if (shouldNavigate) {
                        val score = viewModel.correctAnswersCheck
                        val total = (uiState as QuizUiState.Success).data.results.size
                        navController.navigate("results/$score/$total")
                    }
                }
            }

            is QuizUiState.Error -> {
                Log.d(FINDME, "Error Compose")
                // Do something if the api returns an error with retrieving the data
            }
        }
    }
}
@Composable
fun DisplayQuestion(question: String) {
    Card(
        modifier = Modifier
            .height(240.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = question,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DisplayAnswers(answers: List<String>, viewModel: QuestionDisplayViewModel) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.d("FINDME", "List size: ${answers.size}")
        answers.forEach { answer ->
            Log.d("FINDME", answer)
            AnswerTemplate(answer, viewModel.answerOnClick(answer))
        }
    }
}

@Composable
fun AnswerTemplate(answer: String, onClickListener: () -> Unit){
    Button(
        onClick = onClickListener,
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
    ) {
        Text(answer)
    }
}