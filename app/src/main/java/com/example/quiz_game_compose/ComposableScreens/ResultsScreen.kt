package com.example.quiz_game_compose.ComposableScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DisplayResults(navController: NavController, score: Int, total: Int) {
    Column {
        Text("Total correct Answers: $score out of $total")
        Button({navController.navigate("StartScreen")}) {
            Text("Restart the quiz?")
        }
    }
}