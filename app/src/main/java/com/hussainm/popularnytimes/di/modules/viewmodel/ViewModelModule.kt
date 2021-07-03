package com.hussainm.popularnytimes.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import com.hussainm.popularnytimes.articles.view.PopularArticlesViewModel
import com.hussainm.popularnytimes.viewmodel.NavigationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularArticlesViewModel::class)
    abstract fun providePopularArticleViewModel(viewModel: PopularArticlesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NavigationViewModel::class)
    abstract fun provideNavigationViewModel(viewModel: NavigationViewModel): ViewModel
}