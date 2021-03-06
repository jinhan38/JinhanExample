package com.jinhanexample.recyclerView.grouping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.jinhanexample.R
import com.jinhanexample.fragment.RecyclerViewAdapter
import com.jinhanexample.recyclerView.dragAndSwipe.OnRVDragListener

class GroupingRecyclerViewActivity : AppCompatActivity() {

    companion object {

        private const val TAG = "GroupingRecyclerViewAct"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grouping_recycler_view)

        var groupingRecyclerView = findViewById<RecyclerView>(R.id.groupingRecyclerView)


//      contentData와 headerData의 가상 데이터를 만들어줍니다.
        var contentList = ArrayList<ContentData>()
        contentList.clear()
        for (i in 0..4) {
            contentList.add(ContentData(0, "쇼펜하우어"))
        }
        for (i in 0..4) {
            contentList.add(ContentData(4, "니체"))
        }
        for (i in 0..4) {
            contentList.add(ContentData(9, "칸트"))
        }
        for (i in 0..4) {
            contentList.add(ContentData(17, "사르트르"))
        }

        var headerList = ArrayList<HeaderData>()
        headerList.add(HeaderData(0))
        headerList.add(HeaderData(4))
        headerList.add(HeaderData(9))
        headerList.add(HeaderData(17))



        //headerList에 입력된 count 값을 기준으로 하여
        //headerList와 contentList의 값을 parentList에 차례대로 넣어줬습니다.
        val parentList = ArrayList<ParentData>()
        for (i in 0 until headerList.size) {
            parentList.add(headerList[i])
            for (j in 0 until contentList.size) {
                if (contentList[j].count == headerList[i].count) {
                    parentList.add(contentList[j])
                }
            }
        }


        groupingRecyclerView.apply {
            setHasFixedSize(true)
            var dragAndSwipeRVAdapter = GroupingRVAdapter(parentList)
            adapter = dragAndSwipeRVAdapter
        }

    }

}