package com.example.evanrobertsoncomp304lab2_ex1.di

import com.example.evanrobertsoncomp304lab2_ex1.data.TaskRepositoryImpl
import com.example.evanrobertsoncomp304lab2_ex1.viewModels.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val appModules = module {
    single<TaskRepositoryImpl> { TaskRepositoryImpl() }
    viewModel { TaskViewModel(get()) }
}