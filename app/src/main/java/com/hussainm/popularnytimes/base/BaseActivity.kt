package com.hussainm.popularnytimes.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hussainm.popularnytimes.PopularNYTimesApp
import com.hussainm.popularnytimes.R
import com.hussainm.popularnytimes.di.modules.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PopularNYTimesApp.appComponent.inject(this)
    }

    /**
     * @return A ViewModel tied to the lifecycle of the specified activity
     */
    protected inline fun <reified VM: ViewModel> getViewModel(activity: AppCompatActivity): VM {
        return ViewModelProvider(activity, viewModelFactory).get(VM::class.java)
    }
}