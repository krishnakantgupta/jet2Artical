package com.kk.jet2articalassignment.view.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kk.jet2articalassignment.R
import io.reactivex.disposables.CompositeDisposable


class ArticleListActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)
    }
}
