package com.arshapshap.surftraineetask

import android.app.Application
import com.arshapshap.surftraineetask.di.AppComponent
import com.arshapshap.surftraineetask.di.DaggerAppComponent

open class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}