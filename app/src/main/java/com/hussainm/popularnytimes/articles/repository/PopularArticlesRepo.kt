package com.hussainm.popularnytimes.articles.repository

import com.hussainm.popularnytimes.articles.model.PopularArticleBase
import com.hussainm.popularnytimes.network.RemoteDataStore
import com.hussainm.popularnytimes.network.utility.Result
import com.hussainm.popularnytimes.utility.getErrorMessage
import javax.inject.Inject

class PopularArticlesRepo @Inject constructor(
    private val remoteDataStore: RemoteDataStore
) : PopularArticlesRepoImpl {

    override suspend fun getPopularArticles(period: Int): Result<PopularArticleBase> {
        val response = remoteDataStore.fetchPopularArticles(period)

        return if (response.isSuccessful && response.body() != null)
            Result.success(response.body()!!) // Bang bang operator works here since we are checking if the [response.body] is null or not
        else
            Result.error(response.errorBody().getErrorMessage())
    }

}