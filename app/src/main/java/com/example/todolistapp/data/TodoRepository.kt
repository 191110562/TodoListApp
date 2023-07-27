package com.example.todolistapp.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodoStream(): Flow<List<Todo>>
    fun getTodoStream(id: Int): Flow<Todo?>
    suspend fun insertItem(item: Todo)
    suspend fun deleteItem(item: Todo)
    suspend fun updateItem(item: Todo)
}