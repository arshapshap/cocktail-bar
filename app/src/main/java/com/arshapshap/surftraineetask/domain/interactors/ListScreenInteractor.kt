package com.arshapshap.surftraineetask.domain.interactors

import com.arshapshap.surftraineetask.domain.models.Cocktail
import com.arshapshap.surftraineetask.domain.repositories.CocktailRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListScreenInteractor @Inject constructor(
    private val repository: CocktailRepository
) {

    suspend fun getCocktails(): List<Cocktail> {
        return repository.getCocktails()
    }
}