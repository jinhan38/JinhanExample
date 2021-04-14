package com.jinhanexample.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class KotlinPlayGroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_play_ground)

        runBlocking {
            try {
                failedConcurrencySum()
            } catch (e: ArithmeticException) {
                print("ArithmeticException 발생함")
            }
        }
    }


    private suspend fun failedConcurrencySum(): Int = coroutineScope {

        val one = async<Int> {
            try {
                print("첫번째 진입")
                delay(2000)
                100
            } finally {
                print("첫번째 one cancel -> finally")
            }
        }

        val two = async<Int> {

            delay(1000)
            print("두번째 two throw exception")
            throw ArithmeticException()
        }
        
        val three = async<Int> {
            try {
                print("세번째 진입")
                delay(3000)
                300
            } finally {
                print("세번째 three cancel -> finally")
            }
        }


        val first = one.await()
        val second = two.await()
        val third = three.await()
        first + second + third
    }

    fun print(msg: String) {
        println("Kotlin test : $msg")

    }

}


