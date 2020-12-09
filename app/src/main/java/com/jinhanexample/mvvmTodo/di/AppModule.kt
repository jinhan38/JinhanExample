package com.jinhanexample.mvvmTodo.di

import android.app.Application
import androidx.room.Room
import com.jinhanexample.mvvmTodo.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

//Module은 객체(object)에 붙인다.
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    //provides는 메소드에 붙인다.
    //database를 생성하는 메소드
    //파라미터로 받은 application에 TaskDatabase를 생성한다.
    @Provides
    @Singleton
    fun provideDatabase(
        app : Application,
        callback : TaskDatabase.Callback
    ) =
        Room.databaseBuilder(app, TaskDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Provides
    fun provideTaskDao(db : TaskDatabase) = db.taskDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

//Annotation 만드는 방법
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope