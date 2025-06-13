package com.example.quiz_game_compose.ComposableScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quiz_game_compose.QuizUIView.QuestionDisplayViewModel
import com.example.quiz_game_compose.R

/**
 * Text view that explains the rules of the game
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navController: NavController, viewModel: QuestionDisplayViewModel = hiltViewModel()) {
    var difficultyExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }

    val difficultyOptions = listOf(
        stringResource(R.string.start_quiz_difficulty_easy),
        stringResource(R.string.start_quiz_difficulty_medium),
        stringResource(R.string.start_quiz_difficulty_hard)
    )
    val categoryOptions = listOf(
        stringResource(R.string.start_quiz_category_general),
        stringResource(R.string.start_quiz_category_film),
        stringResource(R.string.start_quiz_category_video_games)
    )
    val defaultDifficulty = stringResource(R.string.difficulty_default_text)
    val defaultCategory = stringResource(R.string.category_default_text)

    var selectedDifficulty by remember { mutableStateOf(defaultDifficulty) }
    var selectedCategory by remember { mutableStateOf(defaultCategory) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.start_quiz_rules),
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
        )

        // Difficulty DropDown
        DropDownBaseSelector(
            defaultDropDownText = "Select Difficulty",
            options = difficultyOptions,
            selectedOptions = selectedDifficulty,
            onOptionSelected = {selectedDifficulty = it},
            expanded = difficultyExpanded,
            onExpandedToggle = {difficultyExpanded = it}
        )

        //Category Dropdown
        DropDownBaseSelector(
            defaultDropDownText = "Select Category",
            options = categoryOptions,
            selectedOptions = selectedCategory,
            onOptionSelected = {selectedCategory = it},
            expanded = categoryExpanded,
            onExpandedToggle = {categoryExpanded = it}
        )

        Button(
            onClick = {
                if (selectedCategory != defaultCategory && selectedDifficulty != defaultDifficulty){
                    viewModel.retrieveQuestions(10, selectedCategory, selectedDifficulty, "multiple")
                    navController.navigate("quiz")
                }
            }
        ) {
            Text("Start Game")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownBaseSelector(
    defaultDropDownText: String,
    options: List<String>,
    selectedOptions: String,
    onOptionSelected: (String) -> Unit,
    expanded: Boolean,
    onExpandedToggle:(Boolean) -> Unit
){
    Box(modifier =
        Modifier.fillMaxWidth()
        .padding(bottom = 20.dp)
    ){
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { onExpandedToggle(true) }
        ) {
            TextField(
                value = selectedOptions,
                onValueChange = {},
                readOnly = true,
                label = { Text(defaultDropDownText) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryEditable),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { onExpandedToggle(false) }
            ) {
                options.forEach { difficulty ->
                    DropdownMenuItem(
                        text = { Text(difficulty) },
                        onClick = {
                            onOptionSelected(difficulty)
                            onExpandedToggle(!expanded)
                        }
                    )
                }
            }
        }
    }
}

/**
 *
 */