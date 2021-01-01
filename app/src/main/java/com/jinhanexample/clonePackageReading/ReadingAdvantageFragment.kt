package com.jinhanexample.clonePackageReading

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jinhanexample.R
import com.jinhanexample.databinding.FragmentReadingAdvantageBinding

class ReadingAdvantageFragment : Fragment() {

    lateinit var b: FragmentReadingAdvantageBinding
    lateinit var title: String
    lateinit var content: String

    companion object {
        private const val TAG = "ReadingAdvantageFragmen"
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalBus.getBus().register(this)


        title = arguments?.getString("title", "title").toString()
        content = arguments?.getString("content", "content").toString()
        Log.d(TAG, "onCreate: 진입 $title")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        b = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_reading_advantage,
            container,
            false
        )

        Log.d(TAG, "onCreateView: 진입")

        b.toolbar.title = title
        b.content = content


        b.toolbar.backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        return b.root
    }

}