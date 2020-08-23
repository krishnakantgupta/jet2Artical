package com.kk.jet2articalassignment.data.api

import com.kk.jet2articalassignment.data.models.ArticalInfo
import io.reactivex.Observable
import io.reactivex.Single

interface ApiService {
    fun getArticles(pageNumber: Int): Observable<List<ArticalInfo>>
}