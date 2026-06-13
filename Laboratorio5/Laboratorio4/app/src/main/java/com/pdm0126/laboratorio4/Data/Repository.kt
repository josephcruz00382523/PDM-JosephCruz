package com.pdm0126.laboratorio4.Data

import com.pdm0126.laboratorio4.model.Task
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getTasks(): Flow<List<Task>>
    suspend fun addTask(task: Task)
    suspend fun deleteTask(id: Int)
    suspend fun toggleComplete(id: Int)
}
