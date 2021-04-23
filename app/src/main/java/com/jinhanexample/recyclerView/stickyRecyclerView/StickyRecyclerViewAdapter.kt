package com.jinhanexample.recyclerView.stickyRecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler
import com.jinhanexample.R
import com.jinhanexample.mvvmSample.java.author.ui.AuthorRecyclerViewAdapter

class StickyRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    StickyHeaderHandler {

    var recyclerItemList: ArrayList<AdapterItem> = ArrayList()

    override fun getAdapterData(): MutableList<*> {
        return recyclerItemList
    }

    companion object {
        const val TYPE_TOP = 0
        const val TYPE_HOLDER = 1
        const val TYPE_EMPTY = 2
        const val TYPE_LIST = 3
        private const val TAG = "StickyRecyclerViewAdapt"
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_TOP
            1 -> TYPE_HOLDER
            2 -> TYPE_EMPTY
            else -> TYPE_LIST
        }
    }

    // 아이템 설정
    fun setItems(recyclerItemList: ArrayList<AdapterItem>) {
        this.recyclerItemList = recyclerItemList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(Companion.TAG, "onCreateViewHolder: viewType : $viewType")
        return when (recyclerItemList[viewType].type) {
            TYPE_TOP -> {
                TopViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.top_item, parent, false))
            }
            TYPE_HOLDER -> {
                HolderViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.hold_item, parent, false))
            }
            TYPE_EMPTY -> {
                EmptyViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.empty_item, parent, false))
            }
            else -> {
                ItemViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false))
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TopViewHolder -> {
                holder.onBindView(recyclerItemList[position].objects)
            }
            is HolderViewHolder -> {
                holder.onBindView(recyclerItemList[position].objects)
            }
            is EmptyViewHolder -> {
                holder.bindView()
            }
            is ItemViewHolder -> {
                holder.bindView()
            }
        }


    }

    override fun getItemCount(): Int = recyclerItemList.size

    inner class TopViewHolder(itemView: View) : CommonViewHolder(itemView) {


        override fun onBindView(item: Data) {

        }

    }

    inner class HolderViewHolder(itemView: View) : CommonViewHolder(itemView) {

        override fun onBindView(item: Data) {

        }

    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView() {}
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView() {}
    }


}

abstract class CommonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBindView(item: Data)
}