package com.jinhanexample.mvvmTodo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "task_table")  // room table 생성
@Parcelize
data class Task(
    val name: String,
    val important: Boolean = false,
    val completed: Boolean = false,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable {

    //생성자에서 만든 created를 가져오는 방법
    // get()메소드와 DateFormat 클래스르 이용했음
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)

}