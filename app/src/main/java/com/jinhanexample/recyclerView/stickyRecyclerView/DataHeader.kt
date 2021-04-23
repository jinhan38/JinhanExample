package com.jinhanexample.recyclerView.stickyRecyclerView

import com.brandongogetap.stickyheaders.exposed.StickyHeader


//StickyHeadr 상속이 중요
class DataHeader(name: String) : Data(name), StickyHeader {
}