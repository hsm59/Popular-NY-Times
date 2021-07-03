package com.hussainm.popularnytimes.articles.view

import androidx.lifecycle.*
import com.hussainm.popularnytimes.articles.model.PopularArticleBase
import com.hussainm.popularnytimes.articles.repository.PopularArticlesRepo
import com.hussainm.popularnytimes.network.utility.Result
import com.hussainm.popularnytimes.utility.ALREADY_VIEWING_ARTICLES
import com.hussainm.popularnytimes.utility.LAST_SEVEN_DAYS
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularArticlesViewModel @Inject constructor(
    private val popularArticlesRepo: PopularArticlesRepo
) : ViewModel() {

    private val _articles = MutableLiveData<Result<PopularArticleBase>>()

    val articles: LiveData<Result<PopularArticleBase>> = _articles

    private var currentSelectedPeriod = 0

    fun fetchArticles(period: Int = LAST_SEVEN_DAYS) {
        viewModelScope.launch {

            if (currentSelectedPeriod != period) {
                currentSelectedPeriod = period

                _articles.value = Result.loading()

                val response = popularArticlesRepo.getPopularArticles(period)

                _articles.value = response
            } else {
                _articles.value = Result.error(ALREADY_VIEWING_ARTICLES)
            }

        }
    }

    /*fun fetchArticles(period: Int = 7) = liveData {

        emit(Result.loading())

        val response = popularArticlesRepo.getPopularArticles(period)

        emit(response)

    }*/

}