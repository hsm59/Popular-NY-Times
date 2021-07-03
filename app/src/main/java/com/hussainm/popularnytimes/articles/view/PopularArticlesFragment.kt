package com.hussainm.popularnytimes.articles.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import com.hussainm.popularnytimes.PopularNYTimesActivity
import com.hussainm.popularnytimes.R
import com.hussainm.popularnytimes.base.BaseFragment
import com.hussainm.popularnytimes.databinding.FragmentPopularArticlesBinding
import com.hussainm.popularnytimes.network.utility.Result
import com.hussainm.popularnytimes.utility.*

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
        makeFetchCall()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_popular_articles, container, false
        )
        setHasOptionsMenu(true)

        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupVMObserver()
        setupAdapterObserver()
    }

    private fun setupAdapterObserver() {
        popularArticlesAdapter.registerAdapterDataObserver(
            scrollToTopAdapterObserver { dataBinding.rvArticles.scrollToPosition(TOP_POSITION) }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home_page, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_options -> displayPopUpMenu(requireActivity().findViewById(R.id.menu_options))
            R.id.menu_search -> {
                // Do nothing for now
            }
        }
        return false
    }

    private fun displayPopUpMenu(view: View) {
        context?.let { nnContext ->
            val popupMenu = PopupMenu(nnContext, view)

            popupMenu.menuInflater.inflate(R.menu.menu_timeline_options, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_today -> makeFetchCall(TODAY)
                    R.id.menu_last_seven -> makeFetchCall(LAST_SEVEN_DAYS)
                    R.id.menu_last_thirty -> makeFetchCall(LAST_THIRTY_DAYS)
                }
                true
            }
            popupMenu.show()
        }

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

    private fun makeFetchCall(period: Int = LAST_SEVEN_DAYS) = popularArticlesVM.fetchArticles(period)


    private fun showProgress(flag: Boolean) = dataBinding.progressBar.toggleVisibility(flag)
}