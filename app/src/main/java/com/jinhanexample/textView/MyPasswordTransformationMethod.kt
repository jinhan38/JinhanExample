package com.jinhanexample.textView

import android.text.method.PasswordTransformationMethod
import android.view.View


/**
 * EditText에 입력된 텍스트를 '●' 모양으로 변경
 */
class MyPasswordTransformationMethod : PasswordTransformationMethod() {

    override fun getTransformation(source: CharSequence?, view: View?): CharSequence? {

        return PasswordCharSequence(source)
    }

    inner class PasswordCharSequence(
        private val mSource: CharSequence?
    ) : CharSequence {

        override val length: Int get() = mSource!!.length

        override fun get(index: Int): Char {
            return '●'
        }

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            return mSource!!.subSequence(startIndex, endIndex)
        }

    }
}