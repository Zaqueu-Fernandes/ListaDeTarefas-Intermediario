package com.exemplo.listadetarefas.data

import com.exemplo.listadetarefas.data.local.TaskDao
import com.exemplo.listadetarefas.data.local.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: Flow<List<TaskEntity>> = taskDao.getAllTasks()

    suspend fun getTaskById(id: Long): TaskEntity? = taskDao.getTaskById(id)

    suspend fun saveTask(id: Long, title: String, description: String) {
        taskDao.insertTask(TaskEntity(id = id, title = title, description = description))
    }
}
