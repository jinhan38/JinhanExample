package com.jinhanexample.recyclerView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityRecyclerViewBinding
import com.jinhanexample.recyclerView.dragAndSwipe.DragAndSwipeRecyclerViewActivity
import com.jinhanexample.recyclerView.flexBoxRecyclerView.FlexRecyclerViewActivity
import com.jinhanexample.recyclerView.grouping.GroupingRecyclerViewActivity
import com.jinhanexample.recyclerView.stickyRecyclerView.StickyRecyclerViewActivity
import com.jinhanexample.recyclerView.tagView.TagViewActivity
import com.jinhanexample.recyclerView.tagView.TagViewActivityJava

class RecyclerViewActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var b : ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view)

        b.childTabControlRecyclerView.setOnClickListener(this)
        b.groupingRecyclerView.setOnClickListener(this)
        b.dragAndSwipeRecyclerView.setOnClickListener(this)
        b.flexBoxRecyclerView.setOnClickListener(this)
        b.tagViewRecyclerView.setOnClickListener(this)
        b.stickyRecyclerView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

        when (p0!!.id) {
            R.id.childTabControlRecyclerView -> {
                startActivity(Intent(this, RecyclerViewChildControlActivity::class.java))
            }
            R.id.groupingRecyclerView -> {
                startActivity(Intent(this, GroupingRecyclerViewActivity::class.java))
            }
            R.id.dragAndSwipeRecyclerView -> {
                startActivity(Intent(this, DragAndSwipeRecyclerViewActivity::class.java))
            }
            R.id.flexBoxRecyclerView -> {
                startActivity(Intent(this, FlexRecyclerViewActivity::class.java))
            }
            R.id.tagViewRecyclerView -> {
                startActivity(Intent(this, TagViewActivityJava::class.java))
            }
            R.id.stickyRecyclerView -> {
                startActivity(Intent(this, StickyRecyclerViewActivity::class.java))
            }
        }
    }
}