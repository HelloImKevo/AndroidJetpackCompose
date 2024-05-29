package com.schanz.todolist.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schanz.todolist.data.models.ToDoTask
import com.schanz.todolist.data.repositories.ToDoRepository
import com.schanz.todolist.util.Action
import com.schanz.todolist.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    // App Bar: Search functionality
    var searchAppBarState by mutableStateOf(SearchAppBarState.CLOSED)
        private set
    var searchTextState by mutableStateOf("")
        private set

    private val _allTasks = MutableStateFlow<List<ToDoTask>>(emptyList())

    // This second variable without underscore will be publicly exposed to our
    // composable where we are going to observe and get notified from our composable.
    val allTasks: StateFlow<List<ToDoTask>> = _allTasks

    fun fetchAllTasksAsync() {
        viewModelScope.launch {
            repository.getAllTasks.collect {
                _allTasks.value = it
            }
        }
    }

    fun handleDatabaseActions(action: Action) {
        when (action) {
            Action.ADD -> {
            }

            Action.UPDATE -> {
            }

            Action.DELETE -> {
            }

            Action.DELETE_ALL -> {
            }

            Action.UNDO -> {
            }

            else -> {
            }
        }
    }

    fun updateAppBarState(newState: SearchAppBarState) {
        searchAppBarState = newState
    }

    fun updateSearchText(newText: String) {
        searchTextState = newText
    }
}
