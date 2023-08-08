package com.arshapshap.surftraineetask.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arshapshap.surftraineetask.data.dao.CocktailDao
import com.arshapshap.surftraineetask.data.mappers.StringListConverter
import com.arshapshap.surftraineetask.data.models.CocktailEntity

@Database(
    version = 1,
    entities = [CocktailEntity::class]
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var instance: AppDatabase? = null

        @Synchronized
        fun get(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "app.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }

    abstract fun cocktailDao(): CocktailDao
}