package com.schanz.todolist.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.schanz.todolist.util.Action
import com.schanz.todolist.util.Constants

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(name = Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {
        // TODO: Handle navigation.
    }
}
