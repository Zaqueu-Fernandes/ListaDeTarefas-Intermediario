package com.exemplo.listadetarefas.navigation

sealed class Screen(val route: String) {
    object TaskList : Screen("task_list")

    object TaskForm : Screen("task_form?taskId={taskId}") {
        const val NEW_TASK_ID = -1L
        fun createRoute(taskId: Long = NEW_TASK_ID) = "task_form?taskId=$taskId"
    }
}
