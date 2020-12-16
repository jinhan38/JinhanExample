package com.jinhanexample.mvvmSample.kotlin.author.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


// AuthorRepository에 접근하여 데이터를 불러온다.
class AuthorViewModel(application: Application) : AndroidViewModel(application) {

    // AuthorRepository 인스턴스 생성
    private val repository = AuthorRepository(application = application)

    //authors 데이터 가져오기
    private val authors = repository.getAll()

    fun getAll() : LiveData<List<AuthorEntity>>{
        return this.authors
    }

    //repository에 접근하여 insert와 delete기능 수행

    fun insert(authorEntity: AuthorEntity){
        repository.insert(authorEntity)
    }

    fun delete(authorEntity: AuthorEntity){
        repository.delete(authorEntity)
    }
}