package com.jinhanexample.playground

import android.os.Bundle
import android.util.Log
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


        val list = listOf<Int>(3, 1, 5, 2)

        val stringList = listOf<String>("AA", "BB", "CC")
        try {
            print("makeString : ${makeString(stringList, "뭐지")}")
        } catch (e: Exception) {
            print("익셉션 : $e")
        }

    }

    fun <T> makeString(list: List<T>, delim: String): String =
        when {
            list.isEmpty() -> "리스트 0"
            list.tail().isEmpty() ->
                "리스트 ${list.head()} ${makeString(list.tail(), delim)}"
            else -> "리스트 ${list.head()} $delim ${makeString(list.tail(), delim)}"

        }

    fun <T> List<T>.head(): T =
        if (this.isEmpty()) {
            throw IllegalArgumentException("head called on empty list")
        } else {
            this[0]
        }

    fun <T> List<T>.tail(): List<T> =
        if (this.isEmpty()) {
            throw IllegalArgumentException("tail called on empty list")
        } else {
//            print("drop ${this.drop(1)}")
            this.drop(1)
        }

    fun sum(list: List<Int>): Int =
        if (list.isEmpty()) {
            0
        } else {
//            print("sum 계산 : ${sum(list.tail())}")
            list.head() + sum(list.tail())
        }

    fun print(msg: String) {
        println("Kotlin test : $msg")

    }

}


