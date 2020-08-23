package com.kk.jet2articalassignment.data.models

import com.google.gson.annotations.SerializedName

data class ArticalInfo(
    val id: String,
    val createdAt: String,
    val content: String,
    val comments: Long = 0,
    val likes: Long = 0,
    @SerializedName("media") val mediaoInfo: List<MediaInfo>,
    @SerializedName("user") val userInfo: List<UserInfo>
)
