package com.example.quiz_game_compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quiz_game_compose.ComposableScreens.DisplayResults
import com.example.quiz_game_compose.ComposableScreens.QuestionScreen
import com.example.quiz_game_compose.ComposableScreens.StartScreen
import com.example.quiz_game_compose.QuizUIView.QuestionDisplayViewModel

/**
 * This is a navigation graph,  you need "routes" to be able to tell where to make a new composable screen
 */
@Composable
fun QuizNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "start"){
        /**
         * Gives customer the option of loading difficulty
         */
        composable("start") {
            StartScreen(navController = navController)
        }
        /**
         * Shows a set amount of questions with answers
         */
        composable("quiz") { navBackStackEntry ->
            // Must keep track of the viewmodel created and pass it on to this class if we want to keep track of the UIState effectively
            val parentEntry = remember(navController) {
                navController.getBackStackEntry("start")
            }
            val viewModel: QuestionDisplayViewModel = hiltViewModel(parentEntry)
            QuestionScreen(navController = navController, viewModel = viewModel)
        }
        /**
         * Displays the results of the quiz, and gives the user the option to play again
         * score: is the amount of correct answers, total: is the total amount of questions in the quiz
         */
        composable("results/{score}/{total}") { navBackStackEntry ->
            val score = navBackStackEntry.arguments?.getString("score")?.toIntOrNull() ?: 0
            val total = navBackStackEntry.arguments?.getString("total")?.toIntOrNull() ?: 0
            DisplayResults(navController,score, total)
        }
    }
}

data class QuizRoutes (
    val quiz: String = "quiz",
    val results: String = "results"
)