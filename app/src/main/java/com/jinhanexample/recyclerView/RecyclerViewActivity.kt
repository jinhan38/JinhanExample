package com.jinhanexample.recyclerView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityRecyclerViewBinding
import com.jinhanexample.recyclerView.dragAndSwipe.DragAndSwipeRecyclerViewActivity
import com.jinhanexample.recyclerView.grouping.GroupingRecyclerViewActivity

class RecyclerViewActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var b : ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view)

        b.childTabControlRecyclerView.setOnClickListener(this)
        b.groupingRecyclerView.setOnClickListener(this)
        b.dragAndSwipeRecyclerView.setOnClickListener(this)
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
        }
    }
}