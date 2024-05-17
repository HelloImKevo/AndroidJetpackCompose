package com.schanz.todolist.util

/**
 * Action that is passed to our List Composable. Used to perform CRUD
 * operations against the Database.
 */
enum class Action {

    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION
}
