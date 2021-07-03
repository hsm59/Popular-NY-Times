package com.hussainm.popularnytimes.articles.view

import androidx.lifecycle.*
import com.hussainm.popularnytimes.articles.model.PopularArticleBase
import com.hussainm.popularnytimes.articles.repository.PopularArticlesRepo
import com.hussainm.popularnytimes.network.utility.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularArticlesViewModel @Inject constructor(
    private val popularArticlesRepo: PopularArticlesRepo
): ViewModel() {

    private val _articles = MutableLiveData<Result<PopularArticleBase>>()

    val articles: LiveData<Result<PopularArticleBase>> = _articles

    fun fetchArticles(period: Int = 7) {
        viewModelScope.launch {

            _articles.value = Result.loading()

            val response = popularArticlesRepo.getPopularArticles(period)

            _articles.value = response

        }
    }

    /*fun fetchArticles(period: Int = 7) = liveData {

        emit(Result.loading())

        val response = popularArticlesRepo.getPopularArticles(period)

        emit(response)

    }*/

}