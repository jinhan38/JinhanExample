package com.jinhanexample.mvvmTodo.data

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class TasksViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao
) : ViewModel() {

}