package com.kk.jet2articalassignment.data.api

import com.kk.jet2articalassignment.data.models.ArticleInfo
import io.reactivex.Observable

class ApiServiceImpl : ApiService {

    override fun getArticles(pageNumber: Int): Observable<List<ArticleInfo>> {
        return NetworkClient.createClient()?.let { it.getArticles(pageNumber) } ?: Observable.just(
            listOf()
        )
    }
}