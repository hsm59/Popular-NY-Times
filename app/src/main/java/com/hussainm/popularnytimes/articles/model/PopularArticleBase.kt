package com.hussainm.popularnytimes.articles.model


import com.google.gson.annotations.SerializedName

data class PopularArticleBase(
    @SerializedName("copyright")
    var copyright: String? = null,
    @SerializedName("num_results")
    var numResults: Int? = null,
    @SerializedName("results")
    var articles: List<Article>? = null,
    @SerializedName("status")
    var status: String? = null
)