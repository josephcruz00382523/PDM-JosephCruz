package com.pdm0126.laboratorio4.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel() {
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    fun onTitleChange(value: String) {
        _title.value = value
    }

    fun onDescriptionChange(value: String) {
        _description.value = value
    }

    fun isFormValid(): Boolean = _title.value.isNotBlank()

    fun reset() {
        _title.value = ""
        _description.value = ""
    }
}
