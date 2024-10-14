package com.example.evanrobertsoncomp304lab2_ex1.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evanrobertsoncomp304lab2_ex1.data.Task
import com.example.evanrobertsoncomp304lab2_ex1.data.TaskRepositoryImpl
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepositoryImpl
) : ViewModel() {

    //Expose the tasks as a StateFlow
    val tasks: StateFlow<List<Task>> = repository.getTasks()

    //Update existing task
    fun updateTask(id: Int, task: Task) {
        viewModelScope.launch {
            repository.updateTask(id, task)
        }
    }

    //Add a new task
    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.addTask(task)
        }
    }
}