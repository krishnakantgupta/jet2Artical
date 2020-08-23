package com.kk.jet2articalassignment.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kk.jet2articalassignment.data.api.ApiHelper
import com.kk.jet2articalassignment.data.repository.ArticleRepository
import com.kk.jet2articalassignment.view.viewmodel.ArticleViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(ArticleRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}