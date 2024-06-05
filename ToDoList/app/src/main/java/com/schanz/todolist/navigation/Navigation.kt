package com.schanz.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.schanz.todolist.navigation.destinations.listComposable
import com.schanz.todolist.navigation.destinations.taskComposable
import com.schanz.todolist.ui.viewmodels.SharedViewModel
import com.schanz.todolist.util.Constants

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val screen = remember(navController) {
        Screens(navController)
    }

    NavHost(
        navController,
        startDestination = Constants.LIST_SCREEN
    ) {
        listComposable(
            sharedViewModel,
            navigateToTaskScreen = screen.task
        )
        taskComposable(
            sharedViewModel,
            navigateToListScreen = screen.list
        )
    }
}
