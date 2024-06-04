package com.schanz.todolist.util

import com.schanz.todolist.data.models.ToDoTask

/**
 * Wraps the response of our List of [ToDoTask].
 */
sealed class RequestState<out T> {

    object Idle : RequestState<Nothing>()

    object Loading : RequestState<Nothing>()

    data class Success<T>(val data: T) : RequestState<T>()

    data class Error(val error: Throwable) : RequestState<Nothing>()
}
