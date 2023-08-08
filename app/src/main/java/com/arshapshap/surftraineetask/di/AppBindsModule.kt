package com.arshapshap.surftraineetask.di

import com.arshapshap.surftraineetask.data.repositories.CocktailRepositoryImpl
import com.arshapshap.surftraineetask.domain.repositories.CocktailRepository
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {

    @Binds
    fun bindCocktailRepositoryImpl(repositoryImpl: CocktailRepositoryImpl): CocktailRepository
}