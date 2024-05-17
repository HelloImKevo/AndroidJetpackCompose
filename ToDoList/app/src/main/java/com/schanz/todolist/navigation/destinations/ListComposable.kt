package com.schanz.todolist.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.schanz.todolist.util.Constants

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit
) {
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(name = Constants.LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
        // TODO: Handle navigation.
    }
}
