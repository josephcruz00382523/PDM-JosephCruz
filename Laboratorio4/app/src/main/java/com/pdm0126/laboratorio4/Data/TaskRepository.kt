package com.pdm0126.laboratorio4.Data

import com.pdm0126.laboratorio4.model.Task

class TaskRepository : Repository {
    private val tasks = mutableListOf<Task>()

    override fun getTasks(): List<Task> = tasks.toList()

    override fun addTask(task: Task) {
        tasks.add(task)
    }

    override fun deleteTask(id: Int) {
        tasks.removeAll { it.id == id }
    }

    override fun toggleComplete(id: Int) {
        val index = tasks.indexOfFirst { it.id == id }
        if (index != -1) {
            tasks[index] = tasks[index].copy(isCompleted = !tasks[index].isCompleted)
        }
    }
}
