package com.hussainm.popularnytimes

import android.app.Application
import com.hussainm.popularnytimes.di.AppComponent
import com.hussainm.popularnytimes.di.DaggerAppComponent
import timber.log.Timber

class PopularNYTimesApp: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    private fun initializeComponent(): AppComponent =
        DaggerAppComponent.factory().create(applicationContext)

    override fun onCreate() {
        super.onCreate()

        appComponent = initializeComponent()

        initTimberLogger()
    }

    private fun initTimberLogger() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}