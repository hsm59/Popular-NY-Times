package com.hussainm.popularnytimes.base

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hussainm.popularnytimes.PopularNYTimesApp
import com.hussainm.popularnytimes.di.modules.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PopularNYTimesApp.appComponent.inject(this)
    }

    /**
     * @return A ViewModel tied to the lifecycle of the current fragment
     */
    protected inline fun <reified VM: ViewModel> getViewModel(): VM {
        return ViewModelProvider(this, viewModelFactory).get(VM::class.java)
    }

    /**
     * @return A ViewModel tied to the lifecycle of the specified fragment
     */
    protected inline fun <reified  VM: ViewModel> getViewModel(fragment: Fragment): VM {
        return ViewModelProvider(fragment, viewModelFactory).get(VM::class.java)
    }

    fun longToast(message: String): Toast =
        Toast.makeText(context, message, Toast.LENGTH_LONG).apply { show() }
}