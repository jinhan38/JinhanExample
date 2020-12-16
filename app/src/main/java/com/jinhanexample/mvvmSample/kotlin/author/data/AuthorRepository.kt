package com.jinhanexample.mvvmSample.kotlin.author.data

import android.app.Application
import androidx.lifecycle.LiveData


//viewModel에서 repository로 접근하여 데이터를 컨트롤한다.
//생성자에서 application을 받음
class AuthorRepository(application: Application) {

    //authorDatabase 인스턴스 생성
    //application은 Context의 자식클래스
    private val authorDatabase = AuthorDatabase.getInstance(application)

    //authorDatabase 의 authorDao() 메소드로 authorDao 생성
    //getInstance가 optional AuthorDatabase 를 반환했기 때문에 !!로 optional unwrapping
    private val authorDao: AuthorDao = authorDatabase!!.authorDao()

    //authorDao.getAll() 메소드로 authorDatabase에 있는 모든 정보 가져오기
    private val authors: LiveData<List<AuthorEntity>> = authorDao.getAll()


    //앞서 가져온 정보를 담은 liveData 배열 authors 반환
    fun getAll(): LiveData<List<AuthorEntity>> {
        return authors
    }

    //authorEntity 입력 함수
    //room db는 메인스레스 접근이 불가능하기 때문에 thread와 이용
    fun insert(authorEntity: AuthorEntity) {
        try {
            val thread = Thread {
                authorDao.insert(authorEntity = authorEntity)
            }
            thread.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //입력받은 authorEntity 삭제
    fun delete(authorEntity: AuthorEntity) {
        try {
            val thread = Thread {
                authorDao.delete(authorEntity = authorEntity)
            }
            thread.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}