package com.jinhanexample.mvvmSample.kotlin.author.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//데이터 클래스를 만들어주세요
//anotation을 이용해 roomDatabase를 만들 수 있습니다.
@Entity(tableName = "authorEntity") //테이블명 입력
data class AuthorEntity(

    //optional Int 타입으로 자동 키값 생성,
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    //컬럼 구성
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "books")
    var books: String,

    @ColumnInfo(name = "nation")
    var nation: String

) {
    constructor() : this(null, "", "", "")
}