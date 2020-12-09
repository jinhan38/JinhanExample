package com.jinhanexample.mvvmTodo.ui.tasks

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jinhanexample.R
import com.jinhanexample.mvvmTodo.data.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks) {
    private val viewModel : TasksViewModel by viewModels()
}