package com.arshapshap.surftraineetask.di

import android.content.Context
import com.arshapshap.surftraineetask.data.AppDatabase
import com.arshapshap.surftraineetask.data.dao.CocktailDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.get(context)
    }

    @Singleton
    @Provides
    fun provideCocktailDao(appDatabase: AppDatabase): CocktailDao {
        return appDatabase.cocktailDao()
    }
}