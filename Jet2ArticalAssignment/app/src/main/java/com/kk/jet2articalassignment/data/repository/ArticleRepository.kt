package com.kk.jet2articalassignment.data.repository

import com.kk.jet2articalassignment.data.api.ApiHelper
import com.kk.jet2articalassignment.data.models.ArticleInfo
import io.reactivex.Observable

class ArticleRepository(private val apiHelper: ApiHelper) {

    fun getArticles(pageNumber: Int): Observable<List<ArticleInfo>> {
        return apiHelper.getArticles(pageNumber)
    }

}
