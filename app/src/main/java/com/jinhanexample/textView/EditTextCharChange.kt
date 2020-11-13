package com.jinhanexample.textView

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.jinhanexample.BaseActivity
import com.jinhanexample.R

class EditTextCharChange : BaseActivity() {
    override fun setupListener() {

    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text_char_change)

        var editText = findViewById<EditText>(R.id.password_login)
        editText?.transformationMethod = MyPasswordTransformationMethod()

    }
}