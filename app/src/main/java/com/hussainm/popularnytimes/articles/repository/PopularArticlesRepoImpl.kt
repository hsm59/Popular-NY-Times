package com.hussainm.popularnytimes.articles.repository

import com.hussainm.popularnytimes.articles.model.PopularArticleBase
import com.hussainm.popularnytimes.network.utility.Result

interface PopularArticlesRepoImpl {

    suspend fun getPopularArticles(period: Int): Result<PopularArticleBase>

}