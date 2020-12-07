package com.jinhanexample.recyclerView.dragAndSwipe

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jinhanexample.R

class DragAndSwipeRVAdapter(var dataList: ArrayList<ParentData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    //getItemViewType 메소드를 override하고
    //dataList의 type값을 return해줍니다.
    override fun getItemViewType(position: Int): Int {
        return dataList[position].getType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //  getItemViewType에서 return한 값들은 viewType으로 넘어옵니다.
        return if (viewType == ParentData.TYPE_HEADER) {
            ViewHolderHeader(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.drag_and_swipe_rv_header_item, parent, false)
            )
        } else {
            ViewHolderContent(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.drag_and_swipe_rv_content_item, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolderHeader) {
            holder.bind(dataList[position] as HeaderData)
        } else if (holder is ViewHolderContent) {
            holder.bind(dataList[position] as ContentData)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItem(dataList: ArrayList<ParentData>) {
        this.dataList = dataList
    }


    inner class ViewHolderHeader(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        val headerView: TextView = itemView.findViewById(R.id.headerView)

        @SuppressLint("SetTextI18n")
        fun bind(headerData: HeaderData) {

            headerView.text = "${headerData.count}번 카테고리 "
        }
    }

    inner class ViewHolderContent(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        val contentView: TextView = itemView.findViewById(R.id.contentView)

        fun bind(contentData: ContentData) {
            contentView.text = contentData.name
        }
    }
}
