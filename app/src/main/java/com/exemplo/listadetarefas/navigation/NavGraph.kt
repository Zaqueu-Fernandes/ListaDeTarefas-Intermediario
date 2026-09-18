package com.exemplo.listadetarefas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.exemplo.listadetarefas.ui.screens.TaskFormScreen
import com.exemplo.listadetarefas.ui.screens.TaskListScreen
import com.exemplo.listadetarefas.ui.viewmodel.TaskViewModel

@Composable
fun NavGraph(viewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.TaskList.route) {

        composable(Screen.TaskList.route) {
            TaskListScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate(Screen.TaskForm.createRoute()) },
                onTaskClick = { taskId ->
                    navController.navigate(Screen.TaskForm.createRoute(taskId))
                }
            )
        }

        composable(
            route = Screen.TaskForm.route,
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.LongType
                    defaultValue = Screen.TaskForm.NEW_TASK_ID
                }
            )
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getLong("taskId") ?: Screen.TaskForm.NEW_TASK_ID
            TaskFormScreen(
                taskId = taskId,
                viewModel = viewModel,
                onTaskSaved = { navController.popBackStack() }
            )
        }
    }
}
