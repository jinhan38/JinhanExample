package com.jinhanexample.codingLecture.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jinhanexample.R

class IntentActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private val REQUEST_CODE: Int = 100
    }

    lateinit var mIdEditText: EditText
    lateinit var mPasswordEditText: EditText
    lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        mIdEditText = findViewById(R.id.id_edit)
        mPasswordEditText = findViewById(R.id.password_edit)
        loginButton = findViewById(R.id.login_button)

        loginButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        var intent = Intent(this, IntentActivity2::class.java)
        intent.putExtra("id", mIdEditText.text.toString())
        intent.putExtra("password", mIdEditText.text.toString())
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            var text = data.getStringExtra("result")
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }


    }
}