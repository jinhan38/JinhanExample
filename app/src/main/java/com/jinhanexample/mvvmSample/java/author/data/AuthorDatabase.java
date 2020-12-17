package com.jinhanexample.mvvmSample.java.author.data;


import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// 코틀린은 [], 자바는 {}
// 코틀린의 Database에서 version 1을 만들었기 때문에 2로 설정
@Database(entities = {AuthorEntity.class}, version = 2)
public abstract class AuthorDatabase extends RoomDatabase {

    public abstract AuthorDao getAuthorDao();

    private static AuthorDatabase instance;

    public static AuthorDatabase getInstance(Context context) {

        if (instance == null) {
//            new Thread(() -> {
            instance = Room.databaseBuilder(context.getApplicationContext(), AuthorDatabase.class, "author")
                    .fallbackToDestructiveMigration()
                    .build();
//            });

        }
        return instance;


    }
}
