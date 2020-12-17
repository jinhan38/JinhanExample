package com.jinhanexample.mvvmSample.java.author.ui;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.jinhanexample.mvvmSample.java.author.data.AuthorEntity;
import com.jinhanexample.mvvmSample.java.author.data.AuthorRepository;

import java.util.List;

public class AuthorViewModel extends AndroidViewModel {

    private AuthorRepository repository;
    private LiveData<List<AuthorEntity>> authorData;

    public AuthorViewModel(Application application) {
        super(application);
        this.repository = new AuthorRepository(application);
        authorData = repository.getAll();
    }

    LiveData<List<AuthorEntity>> getAll() {
        return this.authorData;
    }

    void insert(AuthorEntity authorEntity){
        this.repository.insert(authorEntity);
    }

    void delete(AuthorEntity authorEntity){
        this.repository.delete(authorEntity);
    }


}
