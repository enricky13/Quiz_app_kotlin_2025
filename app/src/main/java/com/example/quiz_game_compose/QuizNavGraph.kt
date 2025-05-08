package com.example.quiz_game_compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quiz_game_compose.QuizUIView.DisplayResults
import com.example.quiz_game_compose.QuizUIView.QuestionScreen

/**
 * This is a navigation graph,  you need "routes" to be able to tell where to make a new composable screen
 */
@Composable
fun QuizNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "quiz"){
        composable("quiz") {
            QuestionScreen(navController = navController)
        }
        composable("results/{score}/{total}") { navBackStackEntry ->
            val score = navBackStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            val total = navBackStackEntry.arguments?.getString("total")?.toIntOrNull() ?: 0
            DisplayResults(score, total)
        }
    }
}