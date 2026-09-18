package com.exemplo.listadetarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.exemplo.listadetarefas.data.TaskRepository
import com.exemplo.listadetarefas.data.local.AppDatabase
import com.exemplo.listadetarefas.navigation.NavGraph
import com.exemplo.listadetarefas.ui.viewmodel.TaskViewModel
import com.exemplo.listadetarefas.ui.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: TaskViewModel by viewModels {
        val database = AppDatabase.getInstance(applicationContext)
        val repository = TaskRepository(database.taskDao())
        TaskViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavGraph(viewModel = viewModel)
                }
            }
        }
    }
}
