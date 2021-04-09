package com.jinhanexample.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.R
import kotlinx.coroutines.*
import java.lang.Exception

class KotlinPlayGroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_play_ground)

        runBlocking {

            //    Dispatchers.Default 는 코루틴이 GlobalScope 에서 실행될 경우에
//    사용되며 공통으로 사용되는 백그라운드 스레드 풀을 이용합니다.
//    즉, launch(Dispatchers.Default) {…} 와 GlobalScope.launch {…} 는
//    동일한 디스패처를 사용합니다.

            //코루틴을 cancel시키기 위해서는 suspend function call 이 필요함
            //yield와 delay()가 suspend function 역할을 함
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {

                try {
                    var nextPrintTime = startTime
                    var i = 0
                    while (i < 5) {
                        if (System.currentTimeMillis() >= nextPrintTime) {
                            yield()
                            println("aaaaaaaaaaa : ${i++}  aaaaaaaaaa")
                            nextPrintTime += 500
                        }
                    }
                } catch (e: Exception) {
                    println("Exception : $e")
                }
            }

            delay(2000)
            job.cancelAndJoin()    // cancel을 시키면 yield가 exception을 던진다

        }

    }


}


