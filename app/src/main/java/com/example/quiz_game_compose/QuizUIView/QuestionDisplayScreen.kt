package com.example.quiz_game_compose.QuizUIView

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuestionScreen(viewModel: QuestionDisplayViewModel = hiltViewModel()){
    val uiState by viewModel.uiState.collectAsState()
    Column {
        when (uiState) {
            is QuizUiState.Loading -> {} // Do something while loading
            is QuizUiState.Success -> {
                val response = (uiState as QuizUiState.Success).data.results
                DisplayQuestion(response[viewModel.currentQuestionIndex].question)
                DisplayAnswers(
                    viewModel.getShuffledAnswers(
                        response[viewModel.currentQuestionIndex].correctAnswer,
                        response[viewModel.currentQuestionIndex].incorrectAnswers
                    ),
                    viewModel
                )
            }

            is QuizUiState.Error -> {}
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
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