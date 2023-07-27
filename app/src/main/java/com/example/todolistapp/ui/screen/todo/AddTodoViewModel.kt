package com.example.todolistapp.ui.screen.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.todolistapp.data.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(

):  ViewModel() {
    var addUiState by mutableStateOf(AddUiState())
        private set

    fun updateUiState(itemDetails: ItemDetails) {
        addUiState =
            AddUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    private fun validateInput(uiState: ItemDetails = addUiState.itemDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && description.isNotBlank() && due_date.isNotBlank()
        }
    }
}

data class AddUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val due_date: String = "",
)

fun ItemDetails.toAdd(): Todo = Todo(
    id = id,
    title = title,
    description = description,
    due_date = due_date
)

fun Todo.toAddUiState(isEntryValid: Boolean = false): AddUiState = AddUiState(
    itemDetails = this.toAddDetails(),
    isEntryValid = isEntryValid
)

fun Todo.toAddDetails(): ItemDetails = ItemDetails(
    id = id,
    title = title,
    description = description,
    due_date = due_date
)