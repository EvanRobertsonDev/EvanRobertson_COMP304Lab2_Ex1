package com.example.evanrobertsoncomp304lab2_ex1.data

import kotlinx.coroutines.flow.StateFlow

interface TaskRepository {
    interface TaskRepository {
        fun getTasks(): StateFlow<List<Task>>
        fun addTask(task: Task)
        fun updateTask(id: Int, task: Task)
    }
}