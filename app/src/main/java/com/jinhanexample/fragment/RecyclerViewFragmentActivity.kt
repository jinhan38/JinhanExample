package com.jinhanexample.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jinhanexample.R
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewFragmentActivity : AppCompatActivity(), View.OnClickListener {

    var dataList = ArrayList<Int>()
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_fragment)

        for (i in 1..100) {
            dataList.add(i)
        }

        (findViewById<FloatingActionButton>(R.id.fab)).setOnClickListener(this)

        recyclerViewAdapter = RecyclerViewAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        var gridLayoutManager = GridLayoutManager(this@RecyclerViewFragmentActivity, 3)

        //둘다 revers이다.레이아웃 매니저의 리버스는 포커스까지 반대로 뒤집힌다.
        //Colloections.reverse == dataList.reverse는 데이터 자체가 reverse된다.
//        gridLayoutManager.reverseLayout = true

        recyclerView.apply {

            setHasFixedSize(true)
            layoutManager = gridLayoutManager

            recyclerViewAdapter.addItem(dataList)
            this.adapter = recyclerViewAdapter
        }


    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.fab -> {
                dataList.reverse()
                recyclerViewAdapter.notifyDataSetChanged()

            }
        }

    }
}

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    var dataList = ArrayList<Int>()

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.num_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_in_fragment_exam, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.textView.text = dataList[position].toString()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItem(dataList: ArrayList<Int>) {
        this.dataList = dataList

    }
}