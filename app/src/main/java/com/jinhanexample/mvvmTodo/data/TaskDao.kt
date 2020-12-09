package com.jinhanexample.mvvmTodo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    //Flow는 코루틴 함수
    //flow는 순차적으로 데이터를 준다? 불확실
    @Query("SELECT * FROM task_table")
    fun getTasks() : Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}