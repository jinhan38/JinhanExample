package com.jinhanexample.recyclerView.grouping

class ContentData(count: Int, var name : String) : ParentData(count) {
    override fun getType(): Int {
        return TYPE_CONTENT
    }

    override fun getData(): Int {
        return count
    }

}