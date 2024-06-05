package com.schanz.todolist.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.schanz.todolist.R
import com.schanz.todolist.data.models.Priority
import com.schanz.todolist.data.models.ToDoTask
import com.schanz.todolist.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    if (selectedTask == null) {
        // Show the form components to create a new To-Do Task.
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        // Show the details of the existing To-Do Task that was selected.
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(
                color = MaterialTheme.colorScheme.onPrimary,
                text = stringResource(id = R.string.add_task)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back_arrow_description),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add_task),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ExistingTaskAppBar(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToListScreen)
        },
        title = {
            Text(
                color = MaterialTheme.colorScheme.onPrimary,
                text = selectedTask.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            DeleteAction(onDeleteClicked = { /* TODO: Handle Delete */ })
            UpdateAction(onUpdateClicked = navigateToListScreen)
        }
    )
}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_icon_description),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
) {
    IconButton(onClick = { onDeleteClicked() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon_description),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_icon_description),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun NewTaskAppBarPreview() {
    NewTaskAppBar(
        navigateToListScreen = {}
    )
}

@Composable
@Preview
private fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(
        selectedTask = ToDoTask(
            id = 0,
            title = "Title: Walk Dog, Pick Up Mail",
            description = "Description of the task. Example: Take the dog" +
                    " for a walk and pick up the mail.",
            priority = Priority.LOW
        ),
        navigateToListScreen = {}
    )
}
