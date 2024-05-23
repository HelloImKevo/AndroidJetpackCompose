package com.schanz.todolist.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schanz.todolist.R
import com.schanz.todolist.components.PriorityItem
import com.schanz.todolist.data.models.Priority
import com.schanz.todolist.ui.theme.LARGE_PADDING
import com.schanz.todolist.ui.theme.TOP_APP_BAR_HEIGHT
import com.schanz.todolist.ui.viewmodels.SharedViewModel
import com.schanz.todolist.util.SearchAppBarState

// Reference:
// https://github.com/stevdza-san/To-Do-Compose/tree/Part-13-Course_Updates

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClickedLogic = {
                    sharedViewModel.updateAppBarState(
                        newState = SearchAppBarState.OPENED
                    )
                },
                onSortClickedLogic = {},
                onDeleteClickedLogic = {}
            )
        }

        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChangeLogic = { newText ->
                    sharedViewModel.updateSearchText(newText = newText)
                },
                onExitSearchMode = {
                    sharedViewModel.updateAppBarState(
                        newState = SearchAppBarState.CLOSED
                    )
                    sharedViewModel.updateSearchText(newText = "")
                },
                onPerformSearch = {}
            )
        }
    }
}

/**
 * Default app bar that shows the Title and three buttons: Search, Filter
 * and More (an Overflow menu). This bar can be swapped for the [SearchAppBar]
 * when the Search button is clicked.
 */
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

/**
 * This bar can be swapped back to the [DefaultListAppBar] when the search
 * behavior is finished.
 */
@Composable
fun SearchAppBar(
    text: String,
    onTextChangeLogic: (String) -> Unit,
    onExitSearchMode: () -> Unit,
    onPerformSearch: (String) -> Unit
) {
    Surface(
        modifier = Modifier
                .fillMaxWidth()
                .height(TOP_APP_BAR_HEIGHT),
        shadowElevation = 8.dp
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChangeLogic(it)
            },
            // The optional placeholder to be displayed when the text field
            // is in focus and the input text is empty.
            placeholder = {
                Text(
                    modifier = Modifier
                            .alpha(0.5f),
                    text = stringResource(R.string.search_hint),
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            ),
            singleLine = true,
            // The left-most icon: a semi-transparent magnifying glass.
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(0.38f),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search_icon_description)
                    )
                }
            },
            // The right-most icon: an "X" to clear input, or exit search mode.
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChangeLogic("")
                        } else {
                            onExitSearchMode()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.close_icon_description)
                    )
                }
            },
            // Change the "Next / Done" button on the soft keyboard to
            // a magnifying glass icon.
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onPerformSearch(text)
                }
            )
        )
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

@Preview
@Composable
private fun SearchAppBarPreview() {
    SearchAppBar(
        text = "Search",
        onTextChangeLogic = {},
        onExitSearchMode = {},
        onPerformSearch = {}
    )
}
