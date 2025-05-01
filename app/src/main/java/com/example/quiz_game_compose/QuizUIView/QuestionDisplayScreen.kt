package com.example.quiz_game_compose.QuizUIView

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun QuestionScreen(viewModel: QuestionDisplayViewModel){
    val uiState by viewModel.uiState.collectAsState()

    when(uiState){
        is QuizUiState.Loading -> {} // Do something while loading
        is QuizUiState.Success -> {}
        is QuizUiState.Error -> {}
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
fun DisplayAnswers(correctAnswer: String, incorrectAnswers: List<String>){

}

@Composable
fun AnswerTemplate(answer: String){
    Button(
        onClick = {/*Put on click logic for selecting wrong and right answer*/},
        modifier = Modifier.height(5.dp)
            .width(5.dp)
    ) {
        Text(answer)
    }
}