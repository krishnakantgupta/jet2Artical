package com.kk.jet2articalassignment.data.models

import com.google.gson.annotations.SerializedName

data class ArticleInfo(
    val id: String,
    val createdAt: String,
    val content: String,
    val comments: Long = 0,
    val likes: Long = 0,
    @SerializedName("media") val mediaInfo: List<MediaInfo>? = null,
    @SerializedName("user") val userInfo: List<UserInfo>? = null
) {
    fun getUserName(): String? {
        return getUser()?.getFullName()
    }

    fun getUser(): UserInfo? {
        if (userInfo?.isNotEmpty()!!)
            return userInfo.get(0)
        return null
    }

    fun getMedia(): MediaInfo? {
        if (mediaInfo?.isNotEmpty()!!)
            return mediaInfo.get(0)
        return null
    }

}
