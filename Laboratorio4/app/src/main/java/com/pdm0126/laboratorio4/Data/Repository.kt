package com.pdm0126.laboratorio4.Data

import com.pdm0126.laboratorio4.model.Task

interface Repository {
    fun getTasks(): List<Task>
    fun addTask(task: Task)
    fun deleteTask(id: Int)
    fun toggleComplete(id: Int)
}
