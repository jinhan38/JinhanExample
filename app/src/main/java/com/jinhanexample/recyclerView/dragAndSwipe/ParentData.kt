package com.jinhanexample.recyclerView.dragAndSwipe

abstract class ParentData(var count: Int) {

    companion object {
        var TYPE_HEADER = 1
        var TYPE_CONTENT = 2
    }

    abstract fun getType(): Int
    abstract fun getData(): Int

}