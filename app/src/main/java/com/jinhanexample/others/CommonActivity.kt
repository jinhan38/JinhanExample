package com.jinhanexample.others

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.jinhanexample.Common
import java.lang.ref.WeakReference

@Suppress("DEPRECATION")
open class CommonActivity : AppCompatActivity() {

    lateinit var handler: MyHandler

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        handler = MyHandler()
    }


    class MyHandler : Handler() {
        lateinit var activity: WeakReference<CommonActivity>

        fun myHandler(activity: CommonActivity) {
            this.activity = WeakReference<CommonActivity>(activity)
        }

        override fun handleMessage(msg: Message) {
            var activity = this.activity.get()
            activity?.handler?.handleMessage(msg)
        }
    }

}

