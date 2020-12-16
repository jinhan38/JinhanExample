package com.jinhanexample.mvvmSample.kotlin.author.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jinhanexample.R
import com.jinhanexample.mvvmSample.kotlin.author.data.AuthorEntity


//RecyclerViewAdapter에 대한 설명은 생략하겠습니다.
//CompletionBlock을 사용해 Long클릭을 했을 때 delete Dialong를 띄워 데이터를 삭제하겠습니다.
class AuthorRecyclerViewAdapter(val onLongClick : (AuthorEntity) -> Unit) : RecyclerView.Adapter<AuthorRecyclerViewAdapter.ViewHolder>() {
    private var authorEntity: List<AuthorEntity> = listOf()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val authorName = itemView.findViewById<TextView>(R.id.authorName)
        val authorBooks = itemView.findViewById<TextView>(R.id.authorBooks)
        val authorNation = itemView.findViewById<TextView>(R.id.authorNation)

        fun bind(authorEntity: AuthorEntity) {
            authorName.text = authorEntity.name
            authorBooks.text = authorEntity.books
            authorNation.text = authorEntity.nation

            //아이템뷰를 클릭했을 때 onLongClick을 이용해 authorEntity데이터 전달
            itemView.setOnClickListener {
                onLongClick(authorEntity)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.author_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(authorEntity[position])
    }

    override fun getItemCount(): Int {
        return authorEntity.size
    }

    fun addItem(authorEntity: List<AuthorEntity>){
        this.authorEntity = authorEntity
        notifyDataSetChanged()

    }
}