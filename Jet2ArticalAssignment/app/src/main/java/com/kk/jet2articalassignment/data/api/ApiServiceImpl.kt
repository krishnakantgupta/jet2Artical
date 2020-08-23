package com.kk.jet2articalassignment.data.api

import com.kk.jet2articalassignment.data.models.ArticalInfo
import io.reactivex.Observable
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    override fun getArticles(pageNumber: Int): Observable<List<ArticalInfo>> {
        return NetworkClient.createClient()?.let { it.getArticles(pageNumber) } ?: Observable.just(
            listOf()
        )
    }
}