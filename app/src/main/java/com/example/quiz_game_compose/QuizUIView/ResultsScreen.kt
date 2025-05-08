package com.example.quiz_game_compose.QuizUIView

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DisplayResults(score: Int, total: Int) {
    Text("Total correct Answers: $score out of $total")
}