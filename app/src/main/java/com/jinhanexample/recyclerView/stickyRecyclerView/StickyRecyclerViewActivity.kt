package com.jinhanexample.recyclerView.stickyRecyclerView

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.brandongogetap.stickyheaders.StickyLayoutManager
import com.brandongogetap.stickyheaders.exposed.StickyHeaderListener
import com.jinhanexample.R
import com.jinhanexample.databinding.ActivityStickyRecyclerViewBinding


class StickyRecyclerViewActivity : AppCompatActivity() {

    lateinit var b: ActivityStickyRecyclerViewBinding
    lateinit var adapter: StickyRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_sticky_recycler_view)


        adapter = StickyRecyclerViewAdapter()
        val adapterItem = ArrayList<AdapterItem>()
        adapterItem.add(AdapterItem(StickyRecyclerViewAdapter.TYPE_TOP, DataHeader("jinhan")))
        adapterItem.add(AdapterItem(StickyRecyclerViewAdapter.TYPE_HOLDER, DataHeader("holder")))
        for (data in peopleInfoList) {
            adapterItem.add(AdapterItem(StickyRecyclerViewAdapter.TYPE_LIST, data))
        }

        adapter.setItems(adapterItem)
        
        val stickyLayoutManager: StickyLayoutManager = TopSnappedStickyLayoutManager(this, adapter)
        stickyLayoutManager.elevateHeaders(true) // Default elevation of 5dp
        b.recyclerView.apply {
            layoutManager = stickyLayoutManager

            adapter = this@StickyRecyclerViewActivity.adapter
            (layoutManager as StickyLayoutManager).setStickyHeaderListener(object :
                StickyHeaderListener {
                override fun headerAttached(headerView: View, adapterPosition: Int) {
                    Log.d(TAG, "headerAttached: ")
                }

                override fun headerDetached(headerView: View, adapterPosition: Int) {
                    Log.d(TAG, "headerDetached: ")
                }
            })
        }

    }


    private val peopleInfoList = listOf(Data("apple", 17),
        Data("banana", 31),
        Data("car", 26),
        Data("dog", 26),
        Data("egg", 24),
        Data("fish", 33),
        Data("great", 40),
        Data("happy", 10),
        Data("ice", 20),
        Data("juice", 32),
        Data("key", 50),
        Data("lonnie", 30),
        Data("mom", 58),
        Data("notice", 11),
        Data("object", 36),
        Data("people", 38),
        Data("queen", 15),
        Data("right", 22),
        Data("sight", 27),
        Data("tiger", 13),
        Data("uv", 29),
        Data("virus", 44),
        Data("wow", 36),
        Data("xylitol", 24),
        Data("yes", 26),
        Data("zoo", 36))

    companion object {
        private const val TAG = "StickyRecyclerViewActiv"
    }

}

