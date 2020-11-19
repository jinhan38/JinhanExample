package com.jinhanexample.codingLecture.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.jinhanexample.R
import kotlinx.android.synthetic.main.activity_intent.*

class IntentActivity2 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent2)
        if (intent != null){
            var id = intent.getStringExtra("id")
            var password = intent.getStringExtra("password")

            Toast.makeText(this, "id : $id, password : $password", Toast.LENGTH_SHORT).show()
            
        }
    }

    override fun onClick(p0: View?) {
        var text = (p0 as Button).text.toString()
        var intent = Intent()
        intent.putExtra("result", text)
        setResult(RESULT_OK, intent)
        finish()
    }
}