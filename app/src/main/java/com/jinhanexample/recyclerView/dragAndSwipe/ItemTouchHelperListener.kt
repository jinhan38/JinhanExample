package com.jinhanexample.recyclerView.dragAndSwipe

interface ItemTouchHelperListener {
    fun onItemMove(fromPosition: Int, afterPosition: Int)
    fun onItemDismiss(position: Int)
}