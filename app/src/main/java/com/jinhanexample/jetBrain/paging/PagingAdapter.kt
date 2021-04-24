package com.jinhanexample.jetBrain.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jinhanexample.R
import com.jinhanexample.databinding.ItemHeaderBinding
import com.jinhanexample.databinding.ItemItemBinding

class PagingAdapter : PagingDataAdapter<DataModel, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<DataModel>() {
            override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
                return if (oldItem is DataModel.Header && newItem is DataModel.Header) {
                    oldItem.title == newItem.title
                } else if (oldItem is DataModel.Item && newItem is DataModel.Item) {
                    oldItem.title == newItem.title
                } else {
                    oldItem is DataModel.Separator && newItem is DataModel.Separator
                }
            }

        }

        private const val TAG = "PagingAdapter"
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = when (viewType) {
        DataType.ITEM.ordinal -> PagingItemViewHolder(parent)
        DataType.HEADER.ordinal -> PagingHeaderViewHolder(parent)
        else -> PagingSeparatorViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PagingItemViewHolder -> holder.bind(getItem(position) as DataModel.Item)
            is PagingHeaderViewHolder -> holder.bind(getItem(position) as DataModel.Header)
        }
    }


    //ordinal 을 사용하면 해당 타입이 enum에서 몇번째 타입인지 알수 있습니다.
    //DataType.ITEM은 DataType에서 두번째에(포지션 1) 있다.
    //때문에 타입의 포지션이 1이면 ITEM을 1이 아니면 다른 타입을 반환
    override fun getItemViewType(position: Int): Int {
        Log.d(TAG, "getItemViewType: ordinal : ${getItem(position)?.type?.ordinal}")
        return getItem(position)?.type?.ordinal ?: DataType.ITEM.ordinal
    }

    class PagingItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
    ) {
        private val dataBinding = ItemItemBinding.bind(itemView)

        fun bind(data: DataModel.Item) {
            dataBinding.item = data
            dataBinding.executePendingBindings()
        }
    }

    class PagingHeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
    ) {
        private val dataBinding = ItemHeaderBinding.bind(itemView)

        fun bind(data: DataModel.Header) {
            dataBinding.item = data
            dataBinding.executePendingBindings()
        }
    }

    class PagingSeparatorViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_seperate, parent, false)
    )
}