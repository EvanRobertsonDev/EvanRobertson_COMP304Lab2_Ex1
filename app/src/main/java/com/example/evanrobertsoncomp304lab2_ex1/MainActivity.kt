package com.example.evanrobertsoncomp304lab2_ex1

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.evanrobertsoncomp304lab2_ex1.di.appModules
import com.example.evanrobertsoncomp304lab2_ex1.views.HomeView
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(appModules)
        }

        setContent {
            HomeView(
                //Goto EditTask screen
                onTaskClick = { task ->
                    val intent = Intent(this, EditTaskActivity::class.java).apply {
                        //Pass taskId
                        putExtra("taskId", task.id)
                    }

                    startActivity(intent)
                },
                //Goto AddTask screen
                onAddTaskClick = {
                    val intent = Intent(this, AddTaskActivity::class.java)
                    startActivity(intent)
                }
            )
        }
    }
}



