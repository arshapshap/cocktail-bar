package com.arshapshap.surftraineetask.data.dao

import androidx.room.*
import com.arshapshap.surftraineetask.data.models.CocktailEntity

@Dao
abstract class CocktailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun add(eventEntity: CocktailEntity): Long

    @Update
    abstract suspend fun update(eventEntity: CocktailEntity)

    @Query("DELETE FROM Cocktail WHERE cocktail_id = :id")
    abstract suspend fun deleteById(id: Long)

    @Query("SELECT * FROM Cocktail")
    abstract suspend fun getAll(): List<CocktailEntity>

    @Query("SELECT * FROM Cocktail WHERE cocktail_id =:id")
    abstract suspend fun getById(id: Long): CocktailEntity?
}