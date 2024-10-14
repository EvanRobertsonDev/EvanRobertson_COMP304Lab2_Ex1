package com.example.evanrobertsoncomp304lab2_ex1.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskRepositoryImpl: TaskRepository {
    //List of Tasks using Stateflow
    private val tasks = MutableStateFlow<List<Task>>(emptyList())

    //Returns list of tasks as StateFlow
    fun getTasks(): StateFlow<List<Task>> = tasks.asStateFlow()

    //Adds new task to list
    fun addTask(task: Task) {
        // Get the current list, add the new task, and update the flow
        tasks.value += task
    }

    //Update task using its id
    fun updateTask(id: Int, task: Task) {
        val currentTasks = tasks.value.toMutableList()
        currentTasks[id] = task

        // Update list
        tasks.value = currentTasks
    }
}