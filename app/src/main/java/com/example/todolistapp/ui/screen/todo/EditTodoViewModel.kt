package com.example.todolistapp.ui.screen.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.todolistapp.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditTodoViewModel  @Inject constructor(
    savedStateHandle: SavedStateHandle
):  ViewModel() {
    var itemUiState by mutableStateOf(AddUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[Screen.DetailTodo.itemIdArg])

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && description.isNotBlank() && due_date.isNotBlank()
        }
    }
}