package com.hussainm.popularnytimes.articledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.hussainm.popularnytimes.R
import com.hussainm.popularnytimes.base.BaseFragment
import com.hussainm.popularnytimes.databinding.FragmentArticleDetailsBinding

class ArticleDetailsFragment : BaseFragment() {

    private val args by navArgs<ArticleDetailsFragmentArgs>()

    private lateinit var dataBinding: FragmentArticleDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_article_details,
            container,
            false
        )

        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
    }

    private fun setupData() {
        dataBinding.articleItem = args.articleData
    }
}