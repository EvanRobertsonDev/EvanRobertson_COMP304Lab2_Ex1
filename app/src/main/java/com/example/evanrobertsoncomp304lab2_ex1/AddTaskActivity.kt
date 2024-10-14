package com.example.evanrobertsoncomp304lab2_ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.evanrobertsoncomp304lab2_ex1.views.CreateTaskScreen

class AddTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateTaskScreen(
                onAddTaskClick = {
                    //Return to home
                    finish()
                }
            )
        }
    }
}