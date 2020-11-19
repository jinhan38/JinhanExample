package com.jinhanexample.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jinhanexample.R


class ColorFragment : Fragment() {


    var backgroundColor = Color.RED

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_color, container, false)

        view.setBackgroundColor(backgroundColor)
        return view
    }


    /**
     * setColor 메소드가 onCreateView보다 먼저 실행되어버린다.
     * 라이프사이클에 대한 이해가 필요하다.
     * 부모뷰에서 fragment를 replace하고 commit을 치고, setColor메소드를 호출했다.
     * 그런데 replace한 프래그먼트의 view가 null이라는 것은
     * 아직 onCreateView 사이클에 진입하지 않아서 View를 그리지 못했다는 것이다.
     * 그래서 전역으로 뺐다.
     */
    fun setColor(color: Int) {
        //프래그먼트 안에서는 view / getView를 이용해 자신을 불러올 수 있음
        backgroundColor = color
        if (view != null) {
            Log.d("TAG", "setColor: not null")
            view?.setBackgroundColor(color)
        } else {
            Log.d("TAG", "setColor: null")
        }

    }

}