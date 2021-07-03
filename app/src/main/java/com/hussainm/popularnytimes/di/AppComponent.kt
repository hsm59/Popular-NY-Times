package com.hussainm.popularnytimes.di

import android.content.Context
import com.hussainm.popularnytimes.base.BaseActivity
import com.hussainm.popularnytimes.base.BaseFragment
import com.hussainm.popularnytimes.di.modules.NetworkModule
import com.hussainm.popularnytimes.di.modules.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        // With @BindInstance, the context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(baseFragment: BaseFragment)
    fun inject(baseActivity: BaseActivity)
}