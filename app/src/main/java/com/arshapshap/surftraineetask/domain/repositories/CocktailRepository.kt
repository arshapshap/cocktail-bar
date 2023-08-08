package com.arshapshap.surftraineetask.domain.repositories

import com.arshapshap.surftraineetask.domain.models.Cocktail

interface CocktailRepository {

    suspend fun getCocktails(): List<Cocktail>

    suspend fun getCocktailById(id: Long): Cocktail?

    suspend fun addCocktail(cocktail: Cocktail): Long

    suspend fun updateCocktail(cocktail: Cocktail)
}