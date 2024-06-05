package com.schanz.todolist.ui.screens.task

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.schanz.todolist.data.models.ToDoTask
import com.schanz.todolist.ui.viewmodels.SharedViewModel
import com.schanz.todolist.util.Action

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
fun TaskScreen(
    sharedViewModel: SharedViewModel,
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    }
                }
            )
        },
        content = { padding ->
        }
    )
}
