package com.arshapshap.surftraineetask.domain.interactors

import com.arshapshap.surftraineetask.domain.models.Cocktail
import com.arshapshap.surftraineetask.domain.repositories.CocktailRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailsInteractor @Inject constructor(
    private val repository: CocktailRepository
) {

    suspend fun getCocktails(): List<Cocktail> {
        return repository.getCocktails()
    }

    suspend fun getCocktailById(id: Long): Cocktail? {
        return repository.getCocktailById(id)
    }

    suspend fun addCocktail(cocktail: Cocktail): Long {
        return repository.addCocktail(cocktail)
    }

    suspend fun updateCocktail(cocktail: Cocktail) {
        repository.updateCocktail(cocktail)
    }
}