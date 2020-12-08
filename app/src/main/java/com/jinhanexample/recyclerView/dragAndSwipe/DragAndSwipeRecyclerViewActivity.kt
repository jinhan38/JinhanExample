package com.jinhanexample.recyclerView.dragAndSwipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.jinhanexample.R

class DragAndSwipeRecyclerViewActivity : AppCompatActivity(), OnRVDragListener {

    lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_and_swipe_recycler_view)
        var dragAndSwipeRecyclerView = findViewById<RecyclerView>(R.id.dragAndSwipeRecyclerView)

        var dataList = ArrayList<String>()

        for (i in 0..30) {
            dataList.add(i.toString())
        }


        dragAndSwipeRecyclerView.apply {
            var dragAndSwipeRVAdapter =
                DragAndSwipeRVAdapter(dataList, this@DragAndSwipeRecyclerViewActivity)

            var callback: ItemTouchHelper.Callback = ItemMoveCallback(dragAndSwipeRVAdapter)
            itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(this)

            setHasFixedSize(true)
            adapter = dragAndSwipeRVAdapter
        }
    }

    override fun onDragListener(holder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(holder)
    }


}