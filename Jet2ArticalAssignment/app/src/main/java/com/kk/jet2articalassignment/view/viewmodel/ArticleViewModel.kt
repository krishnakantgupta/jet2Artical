package com.kk.jet2articalassignment.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kk.jet2articalassignment.data.api.ApiHelper
import com.kk.jet2articalassignment.data.api.ApiServiceImpl
import com.kk.jet2articalassignment.data.models.ArticleInfo
import com.kk.jet2articalassignment.data.repository.ArticleRepository
import com.kk.jet2articalassignment.utils.LoadingStatusDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ArticleViewModel(private val articleRepository: ArticleRepository) : ViewModel() {
    private val articles = MutableLiveData<LoadingStatusDetails<List<ArticleInfo>>>()
    private val compositeDisposable = CompositeDisposable()
    private var pageNumber = 1

    init {
        fetchArticles(pageNumber, false)
    }

    private fun fetchArticles(pageNumber: Int, loadMoreData: Boolean) {
        if (loadMoreData) {
            articles.postValue(LoadingStatusDetails.loadingMore(null))
        } else {
            articles.postValue(LoadingStatusDetails.loading(null))
        }
        compositeDisposable.add(
            ArticleRepository(ApiHelper(ApiServiceImpl()))
                .getArticles(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    articles.postValue(LoadingStatusDetails.success(userList))
                }, { throwable ->
                    articles.postValue(
                        LoadingStatusDetails.error(
                            "Error:" + throwable.message,
                            null
                        )
                    )
                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getArticles(): LiveData<LoadingStatusDetails<List<ArticleInfo>>> {
        return articles
    }

    fun loadMore() {
        fetchArticles(++pageNumber, true)
    }
}