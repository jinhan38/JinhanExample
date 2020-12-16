package com.jinhanexample.mvvmSample.kotlin.author.data

import androidx.lifecycle.LiveData
import androidx.room.*


//데이터 입력을 위한 DAO 인터페이스
@Dao
interface AuthorDao {

    //authorEntity의 모든 데이터를 가져오며
    //정렬 기준은 id 오름차순(ASC = ascending)
    @Query("SELECT * FROM authorEntity ORDER BY id ASC")
    fun getAll() : LiveData<List<AuthorEntity>> // 라이브데이터 타입으로 반환

    //OnConflictStrategy.REPLACE는 primaryKey(여기선 id)가 겹칠 때 덮어쓰도록 한다.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(authorEntity: AuthorEntity)//authorEntity 데이터 입력

    @Delete
    fun delete(authorEntity: AuthorEntity)//authorEntity 데이터 삭제


}