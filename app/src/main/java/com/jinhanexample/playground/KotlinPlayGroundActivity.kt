package com.jinhanexample.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingSource
import com.jinhanexample.R
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException
import java.lang.StringBuilder

class KotlinPlayGroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_play_ground)

        print("factorial : ${Factorial.factorial(3)}")
        val list = listOf<Int>(3, 4, 1, 56)
        print("sum : ${sum(list)}")

    }

    fun <T> head(list: List<T>): T =
        if (list.isEmpty()) {
            throw IllegalArgumentException("head called on empty list")
        } else {
            list[0]
        }

    fun <T> tail(list: List<T>): List<T> =
        if (list.isEmpty()) {
            throw IllegalArgumentException("tail called on empty list")
        } else {
            list.drop(1)
        }

    fun sum(list: List<Int>): Int =
        if (list.isEmpty()) {
            0
        } else {
            head(list) + sum(tail(list))
        }

//    fun sum(list: List<Int>): Int = if (list.isEmpty()) 0 else list[0] + sum(list.drop(1))


    fun print(msg: String) {
        println("Kotlin test : $msg")

    }


    object Factorial {
        val factorial: (Int) -> Int by lazy {
            { n: Int ->
                if (n <= 1) n else n * factorial(n - 1)
            }
        }

    }
}


