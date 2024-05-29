package com.schanz.todolist.ui.screens.list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.schanz.todolist.data.models.Priority
import com.schanz.todolist.data.models.ToDoTask
import com.schanz.todolist.ui.theme.LARGE_PADDING
import com.schanz.todolist.ui.theme.PRIORITY_INDICATOR_SIZE
import com.schanz.todolist.ui.theme.TASK_ITEM_ELEVATION

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun ListContent(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    DisplayTasks(tasks = tasks, navigateToTaskScreen = navigateToTaskScreen)
}

@Composable
@ExperimentalAnimationApi
fun DisplayTasks(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    LazyColumn {
        items(
            items = tasks,
            key = { task ->
                task.id
            }
        ) { task ->
            TaskItem(
                toDoTask = task,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        shadowElevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                    .padding(all = LARGE_PADDING)
                    .fillMaxWidth()
        ) {
            Row {
                // Title of the Task list item.
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    // Draw a circle in the top-right corner of the list item, with a
                    // fill color matching the "priority" (White, Green, Yellow, Red).
                    Canvas(
                        modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }
            // Description of the Task list item, underneath the Title.
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
private fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(
            id = 0,
            title = "Title: Walk Dog, Pick Up Mail",
            description = "Description of the task. Example: Take the dog" +
                    " for a walk and pick up the mail.",
            priority = Priority.MEDIUM
        ),
        navigateToTaskScreen = {}
    )
}
