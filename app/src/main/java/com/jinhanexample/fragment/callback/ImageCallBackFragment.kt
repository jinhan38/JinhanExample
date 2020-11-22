package com.jinhanexample.fragment.callback

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.jinhanexample.R

class ImageCallBackFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var mListener: OnImageClickListener

    interface OnImageClickListener {
        fun onImageClick(view: ImageView, message: String)
    }

    /**
     * 이 메소드를 클릭하는 외부에서 listener를 넘겨준다.
     * 넘겨받은 listener를 this 클래스의 변수로 할당한다.
     * Toast를 구현한다는 것은 외부에서 넘겨줬다.
     *
     * 그리고 Toast에 띄울 메세지는 여기서 정의했다.
     *
     * 다시 말해 무엇을 할지 틀은 외부에서 정해서 넘겨주고, 그 안에 있는 컨텐츠는 여기서 만든다.
     */
    fun setOnImageClickListener(listener: OnImageClickListener) {
        mListener = listener

        // 이미지뷰를 클릭했을 때 리스너가 발생하도록 만들었다.
        imageView.setOnClickListener {
            mListener.onImageClick(imageView, "이미지를 클릭했습니다.")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_image_call_back, container, false)
        imageView = v.findViewById<ImageView>(R.id.callback_image_view)
        return v
    }


}