package com.schanz.todolist.ui.screens.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// Reference:
// https://github.com/stevdza-san/To-Do-Compose/tree/Part-13-Course_Updates

@Composable
fun ListAppBar() {
    DefaultListAppBar()
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DefaultListAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Tasks",
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Preview
@Composable
private fun DefaultListAppBarPreview() {
    DefaultListAppBar()
}
