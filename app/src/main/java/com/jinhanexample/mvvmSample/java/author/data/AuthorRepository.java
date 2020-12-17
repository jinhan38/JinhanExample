package com.jinhanexample.mvvmSample.java.author.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;


public class AuthorRepository {

    public AuthorRepository(Application application) {
        authorDatabase = AuthorDatabase.getInstance(application);
        authorDao = authorDatabase.getAuthorDao();
        authors = authorDao.getAll();
    }

    private AuthorDatabase authorDatabase;
    private AuthorDao authorDao;
    private LiveData<List<AuthorEntity>> authors;

    public LiveData<List<AuthorEntity>> getAll() {
        return authors;
    }


    /**
     * 메인스레드에서 처리 불가
     * @param authorEntity
     */
    public void insert(AuthorEntity authorEntity) {
        try {
            new Thread(() -> {
                authorDao.insert(authorEntity);
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void delete(AuthorEntity authorEntity) {
        try {
            new Thread(() -> {
                authorDao.delete(authorEntity);
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
