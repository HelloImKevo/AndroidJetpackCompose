package com.schanz.todolist.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.schanz.todolist.R
import com.schanz.todolist.components.PriorityItem
import com.schanz.todolist.data.models.Priority
import com.schanz.todolist.ui.theme.LARGE_PADDING

// Reference:
// https://github.com/stevdza-san/To-Do-Compose/tree/Part-13-Course_Updates

@Composable
fun ListAppBar() {
    DefaultListAppBar(
        onSearchClickedLogic = {},
        onSortClickedLogic = {},
        onDeleteClickedLogic = {}
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DefaultListAppBar(
    onSearchClickedLogic: () -> Unit,
    onSortClickedLogic: (Priority) -> Unit,
    onDeleteClickedLogic: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.list_screen_title),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClickedLogic,
                onSortClicked = onSortClickedLogic,
                onDeleteClicked = onDeleteClickedLogic
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(
        onClick = { onSearchClicked() }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_action),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { isExpanded = true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(R.string.sort_action),
            tint = MaterialTheme.colorScheme.onPrimary
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            // Create a DropdownMenuItem for each Priority: None, Low, Medium, High
            Priority.values().forEach { priority ->
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        onSortClicked(priority)
                    },
                    text = {
                        PriorityItem(priority)
                    }
                )
            }
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteClicked: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { isExpanded = true }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_vertical_menu),
            contentDescription = stringResource(R.string.delete_all_action),
            tint = MaterialTheme.colorScheme.onPrimary
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    isExpanded = false
                    onDeleteClicked()
                },
                text = {
                    Text(
                        modifier = Modifier.padding(start = LARGE_PADDING),
                        text = stringResource(R.string.delete_all_action),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClickedLogic = {},
        onSortClickedLogic = {},
        onDeleteClickedLogic = {}
    )
}
