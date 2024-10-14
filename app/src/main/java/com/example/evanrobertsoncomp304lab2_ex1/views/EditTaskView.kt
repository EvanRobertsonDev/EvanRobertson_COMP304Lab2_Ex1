package com.example.evanrobertsoncomp304lab2_ex1.views

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
fun EditTaskView(
    id : Int,
    onSaveClick: () -> Unit
) {
    //Get task viewmodel
    val taskViewModel: TaskViewModel = koinViewModel()

    //Get tasks from repository
    val tasks = taskViewModel.tasks.collectAsState()

    //Prefill fields with existing task data
    var title by remember { mutableStateOf(tasks.value[id].title) }
    var description by remember { mutableStateOf(tasks.value[id].desc) }
    var dueDate by remember { mutableStateOf(tasks.value[id].dueDate) }
    var completeStatus by remember { mutableStateOf(tasks.value[id].isCompleted) }

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
                //Mark as complete or incomplete on click
                onClick = { completeStatus = !completeStatus },
                modifier = Modifier.align(Alignment.End)
            ) {
                //Update text
                Text(if (completeStatus) "Completed" else "Pending")
                Spacer(Modifier.padding(5.dp))
                //Update icon
                Icon(
                    imageVector = if (completeStatus) Icons.Default.Check else Icons.Default.Clear,
                    contentDescription = if (completeStatus) "Completed" else "Pending"
                )
            }
            Button(
                onClick = {
                    //Update existing task
                    taskViewModel.updateTask(
                        id, Task(
                            id = id,
                            title = title,
                            desc = description,
                            dueDate = dueDate,
                            isCompleted = completeStatus
                        )
                    )
                    //Perform event
                    onSaveClick()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Save")
            }
        }
    }
}