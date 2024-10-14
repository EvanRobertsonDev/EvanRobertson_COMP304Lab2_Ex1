package com.example.evanrobertsoncomp304lab2_ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.evanrobertsoncomp304lab2_ex1.views.EditTaskView

class EditTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the task ID passed through Intent
        val taskId = intent.getIntExtra("taskId", -1)

        setContent {
            EditTaskView(
                //Pass in task ID
                id = taskId,
                onSaveClick = {
                    //Return to home
                    finish()
                }
            )
        }
    }
}