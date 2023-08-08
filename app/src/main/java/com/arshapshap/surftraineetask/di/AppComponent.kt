package com.arshapshap.surftraineetask.di

import android.app.Application
import com.arshapshap.surftraineetask.presentation.screens.list.ListFragment
import com.arshapshap.surftraineetask.presentation.screens.list.ListScreenViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(listFragment: ListFragment)

    fun listScreenViewModel(): ListScreenViewModel.Factory
}