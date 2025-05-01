package com.example.quiz_game_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.quiz_game_compose.QuizRepository.QuizApi
import com.example.quiz_game_compose.QuizRepository.QuizRetrofitClient
import com.example.quiz_game_compose.QuizUIView.DisplayAnswers
import com.example.quiz_game_compose.QuizUIView.DisplayQuestion
import com.example.quiz_game_compose.QuizUIView.QuestionDisplayViewModel
import com.example.quiz_game_compose.QuizUIView.QuestionScreen
import com.example.quiz_game_compose.ui.theme.Quiz_game_composeTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: QuestionDisplayViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            Quiz_game_composeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    QuizRetrofitClient.fetchTriviaQuestions(10,9,"easy", "multiple")
//                }
//            }
//        }
//        viewModel = ViewModelProvider(this).get(QuestionDisplayViewModel::class.java)
        // Create the api instance
        val api = QuizRetrofitClient.instance
        // Connect the api service to the viewmodel
        val viewModel = QuestionDisplayViewModel(api)
        setContent {
            QuestionScreen(viewModel)
        }
    }
}