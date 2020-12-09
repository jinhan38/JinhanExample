package com.jinhanexample.mvvmTodo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinhanexample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MVVMTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_v_v_m_todo)
    }
}