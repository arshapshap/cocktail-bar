package com.arshapshap.surftraineetask.di

import com.arshapshap.surftraineetask.presentation.navigation.MainRouter
import com.arshapshap.surftraineetask.presentation.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    fun provideNavigation(): Navigator = Navigator()

    @Singleton
    @Provides
    fun provideMainRouter(navigator: Navigator): MainRouter = navigator
}
