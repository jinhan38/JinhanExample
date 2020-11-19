package com.jinhanexample.fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jinhanexample.R

class BaseFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_fragment)
    }

    fun onClick(view : View){

        when(view.id){
            R.id.fragment_color_change ->{
                startActivity(Intent(this, ColorFragmentActivity::class.java))
            }
            R.id.fragment_recyclerView ->{
                startActivity(Intent(this, RecyclerViewFragmentActivity::class.java))
            }
        }

    }
}