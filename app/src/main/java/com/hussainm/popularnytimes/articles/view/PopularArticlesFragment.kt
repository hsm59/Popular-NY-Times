package com.hussainm.popularnytimes.articles.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hussainm.popularnytimes.PopularNYTimesActivity
import com.hussainm.popularnytimes.R
import com.hussainm.popularnytimes.base.BaseFragment
import com.hussainm.popularnytimes.databinding.FragmentPopularArticlesBinding
import com.hussainm.popularnytimes.network.utility.Result
import com.hussainm.popularnytimes.utility.toggleVisibility
import timber.log.Timber

class PopularArticlesFragment : BaseFragment() {

    private lateinit var dataBinding: FragmentPopularArticlesBinding

    private val popularArticlesVM by lazy { getViewModel<PopularArticlesViewModel>() }

    private val popularArticlesAdapter by lazy {
        PopularArticlesAdapter(
            (requireActivity() as PopularNYTimesActivity).navigationViewModel,
            R.id.navigateToDetails
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchArticles()
    }

    private fun fetchArticles() {
        popularArticlesVM.fetchArticles()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_popular_articles, container, false
        )

        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupVMObserver()
    }

    private fun setupUI() {
        dataBinding.popularArticlesAdapter = popularArticlesAdapter
    }

    private fun setupVMObserver() {

        popularArticlesVM.articles.observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Result.Status.SUCCESS -> {
                    showProgress(false)
                    popularArticlesAdapter.submitList(response?.data?.articles ?: emptyList())
                }

                Result.Status.ERROR -> {
                    showProgress(false)
                    longToast(response.message.orEmpty())
                }
                Result.Status.LOADING -> showProgress(true)
            }
        })

    }


    private fun showProgress(flag: Boolean) = dataBinding.progressBar.toggleVisibility(flag)
}