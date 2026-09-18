package com.exemplo.listadetarefas.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.exemplo.listadetarefas.navigation.Screen
import com.exemplo.listadetarefas.ui.viewmodel.TaskViewModel

/**
 * Tela única de "Cadastro ou Detalhes da Tarefa": funciona tanto para criar uma
 * tarefa nova (taskId == NEW_TASK_ID) quanto para visualizar/editar uma já existente.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormScreen(
    taskId: Long,
    viewModel: TaskViewModel,
    onTaskSaved: () -> Unit
) {
    val isNewTask = taskId == Screen.TaskForm.NEW_TASK_ID

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    LaunchedEffect(taskId) {
        if (!isNewTask) {
            viewModel.getTaskById(taskId)?.let { task ->
                title = task.title
                description = task.description
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(if (isNewTask) "Nova Tarefa" else "Detalhes da Tarefa") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    val idToSave = if (isNewTask) 0L else taskId
                    viewModel.saveTask(idToSave, title, description)
                    onTaskSaved()
                },
                enabled = title.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar")
            }
        }
    }
}
