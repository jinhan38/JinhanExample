package com.jinhanexample.mvvmSample.java.author.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
interface AuthorDao {

    @Query("SELECT * FROM authorEntity ORDER BY id ASC")
    LiveData<List<AuthorEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AuthorEntity authorEntity);

    @Delete
    void delete(AuthorEntity authorEntity);

}
