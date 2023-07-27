package com.example.todolistapp.ui.screen.todo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.todolistapp.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailTodoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val itemId: Int = checkNotNull(savedStateHandle[Screen.DetailTodo.itemIdArg])

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class TodoDetailsUiState(
    val itemDetails: ItemDetails = ItemDetails()
)