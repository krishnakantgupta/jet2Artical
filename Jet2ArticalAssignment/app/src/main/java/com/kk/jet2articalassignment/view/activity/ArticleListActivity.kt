package com.kk.jet2articalassignment.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kk.jet2articalassignment.R
import com.kk.jet2articalassignment.data.api.ApiHelper
import com.kk.jet2articalassignment.data.api.ApiServiceImpl
import com.kk.jet2articalassignment.data.models.ArticleInfo
import com.kk.jet2articalassignment.utils.LoadStatus
import com.kk.jet2articalassignment.view.adapter.ArticleListAdapter
import com.kk.jet2articalassignment.view.base.ViewModelFactory
import com.kk.jet2articalassignment.view.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.activity_article_list.*



class ArticleListActivity : AppCompatActivity() {

    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var articleListAdapter: ArticleListAdapter
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        articleListAdapter = ArticleListAdapter(arrayListOf())
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = articleListAdapter
        initScrollListener()
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (!isLoading) {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == articleListAdapter.itemListSize()) {
                        //bottom of list!
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore(){
        articleViewModel.loadMore()
    }

    private fun setupObserver() {
        articleViewModel.getArticles().observe(this, Observer {
            when (it.status) {
                LoadStatus.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                    isLoading = false
                }
                LoadStatus.LOAD_MORE -> {
                }
                LoadStatus.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                LoadStatus.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    articleListAdapter.showLoadMore(false)
                }
            }
        })
    }

    private fun renderList(articleList: List<ArticleInfo>) {
        articleListAdapter.addData(articleList)
        articleListAdapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        articleViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(ArticleViewModel::class.java)
    }
}
