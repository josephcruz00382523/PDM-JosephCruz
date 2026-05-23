package com.pdm0126.laboratorio4.screen

import androidx.lifecycle.ViewModel
import com.pdm0126.laboratorio4.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GeneralViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    fun addTask(task: Task) {
        _tasks.value = _tasks.value + task
    }

    fun toggleComplete(task: Task) {
        _tasks.value = _tasks.value.map {
            if (it.id == task.id) it.copy(isCompleted = !it.isCompleted) else it
        }
    }

    fun deleteTask(task: Task) {
        _tasks.value = _tasks.value.filter { it.id != task.id }
    }
}
