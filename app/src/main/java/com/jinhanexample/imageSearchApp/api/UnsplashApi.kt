package com.jinhanexample.imageSearchApp.api


import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    companion object {
        const val BASE_URL = "https://api.unsplash.com/";
        const val CLIENT_ID = "TjtnoAX8KaJMvA-GmhmzTJOyOeHN4HIa-QlHv8b1P6I"
    }


    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchPhotos(      // suspend는 비동기 작업일 수 있다는 것을 나타내는 함수
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UnsplashResponse
}