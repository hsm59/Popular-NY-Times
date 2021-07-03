package com.hussainm.popularnytimes.articles.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hussainm.popularnytimes.R
import com.hussainm.popularnytimes.articles.model.Article
import com.hussainm.popularnytimes.databinding.ArticleDataBinding
import com.hussainm.popularnytimes.utility.ARTICLE_DATA_KEY
import com.hussainm.popularnytimes.viewmodel.NavigationViewModel


class PopularArticlesAdapter(
    private val viewModel: NavigationViewModel,
    private val navDirection: Int
) :
    ListAdapter<Article, PopularArticlesAdapter.ArticleItemViewHolder>(ARTICLE_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder =
        ArticleItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_article,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ArticleItemViewHolder(private val binding: ArticleDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(articleItem: Article) {
            binding.articleItem = articleItem
            binding.viewModel = viewModel
            binding.navDirection = navDirection to bundleOf(ARTICLE_DATA_KEY to articleItem)
        }

    }


}

private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem == newItem

}