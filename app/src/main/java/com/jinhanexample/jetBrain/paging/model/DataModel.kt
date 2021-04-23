package com.jinhanexample.jetBrain.paging.model

sealed class DataModel(val type: DataType) {
    data class Item(val title: String) : DataModel(type = DataType.ITEM)
    data class Header(val title: String) : DataModel(type = DataType.HEADER)
    data class Footer(val title: String) : DataModel(type = DataType.FOOTER)
    object Separator : DataModel(DataType.SEPARATOR)
}