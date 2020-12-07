package com.jinhanexample.imageSearchApp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UnsplashPhoto(
    val id: String,
    val description: String?, //null값이 있을 수도 있으니 옵셔널로 선언
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser
) : Parcelable {

    @Parcelize
    data class UnsplashPhotoUrls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String
    ) : Parcelable

    @Parcelize
    data class UnsplashUser(
        val name: String,
        val username: String,
        val instagram_username: String?,
        val profile_image: profileImage
    ) : Parcelable {

        @Parcelize
        data class profileImage(
            val small: String,
            val medium: String,
            val large: String

        ) : Parcelable


        val attributionUrl get() = "https://unsplash.com/$username?utm_source=ImageSearchApp&utm_medium=referral"
    }


}