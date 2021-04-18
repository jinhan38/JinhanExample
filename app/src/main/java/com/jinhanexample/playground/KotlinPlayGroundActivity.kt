package com.jinhanexample.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingSource
import com.jinhanexample.R
import kotlinx.coroutines.*
import java.lang.StringBuilder

class KotlinPlayGroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_play_ground)

        print(add(3, 6).toString())
        print(add(3, 20, 7).toString())
    }

    tailrec fun add(x: Int, y: Int, z: Int): Int = if (y == 0) x else add(inc(x), dec(y), z)

    fun add(a: Int, b: Int): Int {
        var x = a
        var y = b

        while (y != 0) {
            x = inc(x)
            y = dec(y)
        }

        return x
    }

    fun inc(n: Int) = n + 1
    fun dec(n: Int) = n - 1

    fun print(msg: String) {
        println("Kotlin test : $msg")

    }
}


