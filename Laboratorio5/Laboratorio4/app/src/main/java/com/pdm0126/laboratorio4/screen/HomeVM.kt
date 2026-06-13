package com.pdm0126.laboratorio4.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pdm0126.laboratorio4.Data.Repository
import com.pdm0126.laboratorio4.model.Task
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GeneralViewModel(private val repository: Repository) : ViewModel() {

    val tasks = repository.getTasks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(task: Task) = viewModelScope.launch {
        repository.addTask(task)
    }

    fun toggleComplete(task: Task) = viewModelScope.launch {
        repository.toggleComplete(task.id)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.deleteTask(task.id)
    }

    companion object {
        fun factory(repository: Repository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T =
                    GeneralViewModel(repository) as T
            }
    }
}
