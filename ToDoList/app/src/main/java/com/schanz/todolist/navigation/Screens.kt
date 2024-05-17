package com.schanz.todolist.navigation

import androidx.navigation.NavHostController
import com.schanz.todolist.util.Action
import com.schanz.todolist.util.Constants

class Screens(
    navController: NavHostController
) {

    /**
     * Lambda function type that performs navigation using the required [Action].
     */
    val list: (Action) -> Unit = { action ->
        // TODO: Need to consider Proguard / R8 symbol obfuscation.
        navController.navigate(route = "list/$action.name") {
            // Whenever we navigate from our task composable to our list composable,
            // I want to pop up to a list screen and basically remove our task composable
            // from the backstack.
            popUpTo(Constants.LIST_SCREEN) { inclusive = true }
        }
    }

    /**
     * Lambda function that requires a Task ID.
     */
    val task: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }
}
