package com.kk.jet2articalassignment.data.api

class ApiHelper(private val apiService: ApiService) {

    fun getArticles(pageNumber: Int) = apiService.getArticles(pageNumber)

}