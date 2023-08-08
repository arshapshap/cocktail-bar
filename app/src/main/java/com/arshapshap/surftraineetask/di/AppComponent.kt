package com.arshapshap.surftraineetask.di

import android.app.Application
import com.arshapshap.surftraineetask.presentation.MainActivity
import com.arshapshap.surftraineetask.presentation.screens.details.DetailsFragment
import com.arshapshap.surftraineetask.presentation.screens.details.DetailsScreenViewModel
import com.arshapshap.surftraineetask.presentation.screens.editing.EditingFragment
import com.arshapshap.surftraineetask.presentation.screens.editing.EditingScreenViewModel
import com.arshapshap.surftraineetask.presentation.screens.list.ListFragment
import com.arshapshap.surftraineetask.presentation.screens.list.ListScreenViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AppBindsModule::class, DataModule::class, NavigationModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun inject(listFragment: ListFragment)

    fun inject(detailsFragment: DetailsFragment)

    fun inject(editingFragment: EditingFragment)

    fun listScreenViewModel(): ListScreenViewModel.Factory

    fun detailsScreenViewModel(): DetailsScreenViewModel.Factory

    fun editingScreenViewModel(): EditingScreenViewModel.Factory
}