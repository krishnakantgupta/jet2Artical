package com.kk.jet2articalassignment.data.repository

import com.kk.jet2articalassignment.data.api.ApiHelper
import com.kk.jet2articalassignment.data.models.ArticalInfo
import io.reactivex.Observable
import io.reactivex.Single

class ArticleRepository(private val apiHelper: ApiHelper) {

    fun getArticles(pageNumber: Int): Observable<List<ArticalInfo>> {
        return apiHelper.getArticles(pageNumber)
    }

}
