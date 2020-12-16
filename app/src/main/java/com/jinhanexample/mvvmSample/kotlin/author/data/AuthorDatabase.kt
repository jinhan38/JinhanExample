package com.jinhanexample.mvvmSample.kotlin.author.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//Room 데이터베이스 인스턴트생성 클래스
//앞서 만든  AuthorEntity를 entities로 지정, 버전 값 입력
@Database(entities = [AuthorEntity::class], version = 1)
abstract class AuthorDatabase : RoomDatabase() {

    //AuthorDao를 반환하는 추상메소드
    abstract fun authorDao(): AuthorDao

    companion object {

        //싱글톤 패턴 이용
        //optional AuthorDatabase 타입의 instance 변수 선언
        private var instance: AuthorDatabase? = null

        //room databaseBuilder 인스턴스로 생성한 싱글톤 객체 리턴
        //fallbackToDestructiveMigration은 데이터베이스갱신 시 기존 테이블 버리고
        // 테이블 새로 구성
        fun getInstance(context: Context): AuthorDatabase? {

            if (instance == null) {
                synchronized(AuthorDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AuthorDatabase::class.java, "author"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return instance
        }
    }
}