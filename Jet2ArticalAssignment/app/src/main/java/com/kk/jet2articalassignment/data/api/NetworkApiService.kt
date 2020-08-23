package com.kk.jet2articalassignment.data.api

import com.kk.jet2articalassignment.data.models.ArticalInfo
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApiService {
    @GET("api/v1/blogs")
    fun getArticles(@Query("page") pageNo: Int, @Query("limit") limit: Int =10 ): Observable<List<ArticalInfo>>
}