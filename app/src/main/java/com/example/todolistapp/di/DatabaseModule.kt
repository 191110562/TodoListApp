package com.example.todolistapp.di

import android.content.Context
import com.example.todolistapp.data.DefaultTodoRepository
import com.example.todolistapp.data.TodoDao
import com.example.todolistapp.data.TodoDatabase
import com.example.todolistapp.data.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return TodoDatabase.getDatabase(context)
    }

    @Provides
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDao {
        return todoDatabase.todoDao()
    }

    @Provides
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository {
        return DefaultTodoRepository(todoDao)
    }
}