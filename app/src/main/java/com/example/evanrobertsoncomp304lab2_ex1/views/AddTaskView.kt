package com.example.evanrobertsoncomp304lab2_ex1.views

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evanrobertsoncomp304lab2_ex1.data.Task
import com.example.evanrobertsoncomp304lab2_ex1.ui.Typography
import com.example.evanrobertsoncomp304lab2_ex1.viewModels.TaskViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateTaskScreen(onAddTaskClick: () -> Unit) {
    //Get viewmodel
    val taskViewModel: TaskViewModel = koinViewModel()

    //Get tasks from repository
    val tasks = taskViewModel.tasks.collectAsState()

    //Assign default values as empty
    var title by remember { mutableStateOf("") }
    var description  by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    MaterialTheme(
        //Check system theme
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme(),
        typography = Typography
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Task Title") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Task Description") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = dueDate,
                onValueChange = { dueDate = it },
                label = { Text("Due Date") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    //Add new task to repository
                    taskViewModel.addTask(
                        Task(
                            id = tasks.value.size,
                            title = title,
                            desc = description,
                            dueDate = dueDate,
                            isCompleted = false
                        )
                    )
                    //Perform event
                    onAddTaskClick()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Save")
            }
        }
    }
}