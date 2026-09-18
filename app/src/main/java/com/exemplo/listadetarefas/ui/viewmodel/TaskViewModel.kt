package com.exemplo.listadetarefas.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exemplo.listadetarefas.data.TaskRepository
import com.exemplo.listadetarefas.data.local.TaskEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    val tasks: StateFlow<List<TaskEntity>> = repository.allTasks
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun saveTask(id: Long, title: String, description: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            repository.saveTask(id, title, description)
        }
    }

    suspend fun getTaskById(id: Long): TaskEntity? = repository.getTaskById(id)
}
