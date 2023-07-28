package com.example.todolistapp.ui.screen.todo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.TodoRepository
import com.example.todolistapp.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailTodoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TodoRepository
) : ViewModel() {
    private val itemId: Int = checkNotNull(savedStateHandle[Screen.DetailTodo.itemIdArg])
    val uiState: StateFlow<TodoDetailsUiState> =
        repository.getTodoStream(itemId)
            .filterNotNull()
            .map {
                TodoDetailsUiState(itemDetails = it.toAddDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TodoDetailsUiState()
            )

    suspend fun deleteItem() {
        repository.deleteItem(uiState.value.itemDetails.toAdd())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class TodoDetailsUiState(
    val itemDetails: ItemDetails = ItemDetails()
)