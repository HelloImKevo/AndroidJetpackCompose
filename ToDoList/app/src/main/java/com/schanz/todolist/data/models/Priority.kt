package com.schanz.todolist.data.models

import androidx.compose.ui.graphics.Color
import com.schanz.todolist.ui.theme.HighPriorityColor
import com.schanz.todolist.ui.theme.LowPriorityColor
import com.schanz.todolist.ui.theme.MediumPriorityColor
import com.schanz.todolist.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    NONE(NonePriorityColor),
    LOW(LowPriorityColor),
    MEDIUM(MediumPriorityColor),
    HIGH(HighPriorityColor)
}
