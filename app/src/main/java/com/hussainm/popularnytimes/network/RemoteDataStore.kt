package com.hussainm.popularnytimes.network

import com.hussainm.popularnytimes.articles.model.PopularArticleBase
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject


private const val MOST_POPULAR = "mostpopular/"
private const val V2 = "v2/"
private const val VIEWED = "viewed/"
private const val PATH_PERIOD = "pathPeriod"

class RemoteDataStore @Inject constructor(
    private val retrofit: Retrofit
) {

    suspend fun fetchPopularArticles(period: Int): Response<PopularArticleBase> =
        retrofit.create(Client::class.java).fetchPopularArticles(period)
}

private interface Client {

    @GET("$MOST_POPULAR$V2$VIEWED{$PATH_PERIOD}.json")
    suspend fun fetchPopularArticles(@Path(PATH_PERIOD) period: Int): Response<PopularArticleBase>

}