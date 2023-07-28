package com.example.todolistapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * from todo WHERE id = :id")
    fun getTodo(id: Int): Flow<Todo>

    @Query("SELECT * from todo ORDER BY due_date DESC")
    fun getAllTodos(): Flow<List<Todo>>
}