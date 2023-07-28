package com.example.todolistapp.ui.screen.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.TodoRepository
import com.example.todolistapp.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTodoViewModel  @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TodoRepository
):  ViewModel() {

    var itemUiState by mutableStateOf(AddUiState())
        private set


    private val itemId: Int = checkNotNull(savedStateHandle[Screen.DetailTodo.itemIdArg])

    init {
        viewModelScope.launch {
            itemUiState = repository.getTodoStream(itemId)
                .filterNotNull()
                .first()
                .toAddUiState(true)
        }
    }
    suspend fun updateItem() {
        if (validateInput(itemUiState.itemDetails)) {
            repository.updateItem(itemUiState.itemDetails.toAdd())
        }
    }
    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            AddUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && description.isNotBlank() && due_date.isNotBlank()
        }
    }
}