package com.jinhanexample.jetBrain.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.jinhanexample.R
import com.jinhanexample.databinding.ItemLoadingBinding

class LoadStateViewHolder(parent: ViewGroup, private val retry: () -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
    ) {

    private val dataBinding = ItemLoadingBinding.bind(itemView)


    fun bind(loadState: LoadState) {
        dataBinding.apply {

            retryButton.setOnClickListener { retry() }
            isLoading = loadState is LoadState.Loading
            isError = loadState is LoadState.Error
            errorMessage = (loadState as?LoadState.Error)?.error?.message
            executePendingBindings()
        }

    }


}