package com.kk.jet2articalassignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kk.jet2articalassignment.R
import com.kk.jet2articalassignment.data.models.ArticleInfo
import com.kk.jet2articalassignment.utils.FormatorUtils
import kotlinx.android.synthetic.main.view_article_item.view.*


class ArticleListAdapter(
    private val articleList: ArrayList<ArticleInfo>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    var showLoadMore = true

    fun showLoadMore(showLoadMore: Boolean) {
        this.showLoadMore = showLoadMore
        notifyDataSetChanged()
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: ArticleInfo) {
            val userInfo = article.getUser()
            val mediaInfo = article.getMedia()

            itemView.tv_username.text = article.getUserName()
            itemView.tv_designation.text = userInfo?.designation
            itemView.tv_created_time.text = FormatorUtils.getFormatDuration(article.createdAt)
            itemView.tv_like.text = itemView.context.getString(
                R.string.format_like,
                FormatorUtils.convertNumnerIntoInternationalFormat(article.likes)
            )
            itemView.tv_comments.text =
                itemView.context.getString(
                    R.string.format_comment,
                    FormatorUtils.convertNumnerIntoInternationalFormat(article.comments)
                )
            itemView.tv_content.text = article.content
            itemView.tv_media_title.text = mediaInfo?.title
            itemView.tv_artical_url.text = mediaInfo?.url
            var avtarUrl = userInfo?.avatar
            var mediaImageUrl = mediaInfo?.image

            Glide.with(itemView.iv_avtar.context)
                .load(avtarUrl ?: "")
                .apply(RequestOptions().circleCrop())
                .into(itemView.iv_avtar)

            if (mediaImageUrl != null) {
                itemView.iv_media.visibility = View.VISIBLE
                Glide.with(itemView.iv_avtar.context)
                    .load(mediaImageUrl)
                    .into(itemView.iv_media)
            } else {
                itemView.iv_media.visibility = View.GONE
            }
        }
    }

    class LoadMoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (showLoadMore && position == articleList.size) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (showLoadMore && viewType == VIEW_TYPE_LOADING)
            return LoadMoreViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_load_more, parent,
                    false
                )
            )
        else
            return DataViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_article_item, parent,
                    false
                )
            )

    }

    override fun getItemCount(): Int = articleList.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DataViewHolder) {
            holder.bind(articleList[position])
        } else if (holder is LoadMoreViewHolder) {
            holder.bind()
        }
    }

    fun addData(list: List<ArticleInfo>) {
        articleList.addAll(list)
    }

    fun itemListSize(): Int {
        return articleList.size
    }

}