package com.example.todolistapp.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultTodoRepository @Inject constructor(private val todoDao: TodoDao): TodoRepository {
    override fun getAllTodoStream(): Flow<List<Todo>> = todoDao.getAllTodos()
    override fun getTodoStream(id: Int): Flow<Todo?> = todoDao.getTodo(id)

    override suspend fun insertItem(item: Todo) = todoDao.insert(item)

    override suspend fun deleteItem(item: Todo) = todoDao.delete(item)

    override suspend fun updateItem(item: Todo) = todoDao.update(item)
}
