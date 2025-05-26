package com.example.quiz_game_compose.ComposableScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.quiz_game_compose.R

/**
 * Text view that explains the rules of the game
 */
@Composable
fun StartScreen() {
    var expanded by remember { mutableStateOf(false) }
    val difficultyOptions = listOf(
        stringResource(R.string.start_quiz_difficulty_easy),
        stringResource(R.string.start_quiz_difficulty_medium),
        stringResource(R.string.start_quiz_difficulty_hard)
    )
    val category = listOf(
        stringResource(R.string.start_quiz_category_general),
        stringResource(R.string.start_quiz_category_film),
        stringResource(R.string.start_quiz_category_video_games)
    )
    var selectedOption by remember { mutableStateOf("Choose difficulty") }
    Column {
        Text(
            text = stringResource(R.string.start_quiz_rules),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        )
        Box {
            Text(
                text = selectedOption,
                modifier = Modifier.fillMaxWidth()
                    .clickable { expanded = true }
                    .padding(16.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                difficultyOptions.forEach { difficulty ->
                    DropdownMenuItem(
                        text = { Text(difficulty) },
                        onClick = {
                            selectedOption = difficulty.lowercase()
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

//@Composable
//fun DropDownQuizCategories(){
//    Text(
//        text = selectedOption,
//        modifier = Modifier.fillMaxWidth()
//            .clickable { expanded = true }
//            .padding(16.dp)
//    )
//    DropdownMenu(
//        expanded = expanded,
//        onDismissRequest = { expanded = false }
//    ) {
//        difficultyOptions.forEach { difficulty ->
//            DropdownMenuItem(
//                text = { Text(difficulty) },
//                onClick = {
//                    selectedOption = difficulty.lowercase()
//                    expanded = false
//                }
//            )
//        }
//    }
//}

/**
 *
 */