package com.jinhanexample.textView

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.core.view.ViewCompat
import com.jinhanexample.R
import com.jinhanexample.others.Extentions.onMyTextChanged

class EditTextUnderlineColorChange : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text_underline_color_change)
        
        var editText = findViewById<EditText>(R.id.editText)

        editText.onMyTextChanged {

            val colorStateListPurple =
                ColorStateList.valueOf(resources.getColor(R.color.purple_500, null))
            val colorStateListRed =
                ColorStateList.valueOf(resources.getColor(R.color.red, null))

            if (it?.count()!! > 4) {

                ViewCompat.setBackgroundTintList(editText, colorStateListPurple)
            } else {
                ViewCompat.setBackgroundTintList(editText, colorStateListRed)
            }

        }
    }
}