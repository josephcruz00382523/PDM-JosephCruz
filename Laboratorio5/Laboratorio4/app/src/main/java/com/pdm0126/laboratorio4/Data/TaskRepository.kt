package com.pdm0126.laboratorio4.Data

import com.pdm0126.laboratorio4.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) : Repository {
    override fun getTasks(): Flow<List<Task>> = dao.getAllTasks()

    override suspend fun addTask(task: Task) = dao.insertTask(task)

    override suspend fun deleteTask(id: Int) = dao.deleteTask(id)

    override suspend fun toggleComplete(id: Int) = dao.toggleComplete(id)
}
