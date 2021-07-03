package com.hussainm.popularnytimes

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.hussainm.popularnytimes.base.BaseActivity
import com.hussainm.popularnytimes.databinding.ActivityPopularNyTimesBinding
import com.hussainm.popularnytimes.event.EventObserver
import com.hussainm.popularnytimes.viewmodel.NavigationViewModel

class PopularNYTimesActivity : BaseActivity() {

    private lateinit var dataBinding: ActivityPopularNyTimesBinding

    private lateinit var navController: NavController

    val navigationViewModel by lazy { getViewModel<NavigationViewModel>(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_popular_ny_times)
        setupNavigation()
        setupVMObservers()
    }

    private fun setupVMObservers() {
        navigationViewModel.destinationId.observe(this, EventObserver { destinationId ->
            navigate(destinationId)
        })
    }

    private fun setupNavigation() {

        /**
         * [R.id.nav_host_fragment] if kept as <fragment> would work without explicitly using the [getSupportFragmentManager]
         * @see <a href="https://issuetracker.google.com/issues/142847973?pli=1">Google Issue</a> for more information.
         */
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(dataBinding.toolbar, navController)
    }

    private fun navigate(destinationPair: Pair<Int, Bundle?>) {
        if (this::navController.isInitialized)
            navController.navigate(destinationPair.first, destinationPair.second)
    }
}