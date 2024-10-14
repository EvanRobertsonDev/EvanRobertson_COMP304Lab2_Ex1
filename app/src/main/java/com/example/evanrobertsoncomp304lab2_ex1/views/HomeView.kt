package com.example.evanrobertsoncomp304lab2_ex1.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evanrobertsoncomp304lab2_ex1.data.Task
import com.example.evanrobertsoncomp304lab2_ex1.viewModels.TaskViewModel
import org.koin.androidx.compose.koinViewModel
import com.example.evanrobertsoncomp304lab2_ex1.ui.Typography

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HomeView(
    onAddTaskClick: () -> Unit,
    onTaskClick: (Task) -> Unit
) {
    //Get task viewmodel
    val taskViewModel: TaskViewModel = koinViewModel()

    //Get tasks from repository
    val tasks = taskViewModel.tasks.collectAsState()

    MaterialTheme(
        //Check system theme
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme(),
        typography = Typography
    ) {
        Scaffold(
            //Put fab in bottom center
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                FloatingActionButton(
                    //Start AddTask Activity
                    onClick = onAddTaskClick,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Task")
                }
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
                //Create card for each task
                items(tasks.value) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .padding(10.dp)
                            //On Click event using callback
                            .clickable(onClick = { onTaskClick(task) })
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(100.dp),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Icon(
                                //Display status of task
                                imageVector = if (task.isCompleted) Icons.Default.Check else Icons.Default.Clear,
                                contentDescription = if (task.isCompleted) "Completed" else "Pending",
                                modifier = Modifier
                                    .align(Alignment.End)
                            )
                            //Task title
                            Text(
                                text = task.title,
                                fontSize = 30.sp
                            )
                            //Task desc
                            Text(
                                text = task.desc,
                                fontSize = 15.sp
                            )
                        }
                        Text(
                            //Display date in bottom right of card
                            text = "Due: " + task.dueDate + "  ",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .align(Alignment.End)
                        )
                        Spacer(Modifier.padding(5.dp))
                    }
                }
            }
        }
    }

}