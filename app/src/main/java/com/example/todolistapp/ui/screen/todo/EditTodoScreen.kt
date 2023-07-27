package com.example.todolistapp.ui.screen.todo

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolistapp.R
import com.example.todolistapp.TodoListTopAppBar
import com.example.todolistapp.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTodoScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditTodoViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TodoListTopAppBar(
                title = stringResource(R.string.edit_item_title),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        AddEntryBody(
            addUiState = viewModel.itemUiState,
            onItemValueChange = { },
            onSaveClick = { },
            modifier = Modifier.padding(innerPadding)
        )
    }
}