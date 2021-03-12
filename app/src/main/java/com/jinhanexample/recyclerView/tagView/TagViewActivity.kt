package com.jinhanexample.recyclerView.tagView

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import co.lujun.androidtagview.TagContainerLayout
import co.lujun.androidtagview.TagView
import com.jinhanexample.R
import java.util.*


class TagViewActivity : AppCompatActivity() {
    lateinit var mTagContainerLayout: TagContainerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_view)

        val dataList = ArrayList<String>()
        dataList.add("asdfasdfasdf")
        dataList.add("asasdf")
        dataList.add("asdfasdf")
        dataList.add("asdfasddf")
        dataList.add("asda222222222fasdfasdf")
        dataList.add("asdfasdfvvvasdf")
        dataList.add("asdfasxcvdfasdf")
        dataList.add("asdfsdf")
        dataList.add("asdf12341234sdf")
        dataList.add("asdf123412341234sdf")
        dataList.add("asdfㅁㄴㅇ라ㅓㄴ이ㅏ런ㅇsdf")
        dataList.add("asdfㅍㅁㅍㅍㅍsdf")
        dataList.add("asㄹㄹㄹㄹ")
        dataList.add("asdㅁㅁㅁㅁㅁfsdf")


        mTagContainerLayout = findViewById<View>(R.id.tagcontainerLayout) as TagContainerLayout
        mTagContainerLayout.tags = dataList



    }


    private fun tagInit(){
        mTagContainerLayout.tagBackgroundColor = Color.WHITE
        mTagContainerLayout.tagBorderColor = Color.GRAY
        mTagContainerLayout.tagTextColor = Color.BLACK
    }
}