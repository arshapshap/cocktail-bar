package com.arshapshap.surftraineetask.data.repositories

import com.arshapshap.surftraineetask.domain.models.Cocktail
import com.arshapshap.surftraineetask.domain.repositories.CocktailRepository
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor() : CocktailRepository {

    override suspend fun getCocktails(): List<Cocktail> {
        return listOf(
            Cocktail(name = "Pink Lemonade",
                image = null,
                description = "",
                recipe = "",
                ingredients = listOf()),
            Cocktail(name = "Mojito mocktail", image = null,
                description = "",
                recipe = "",
                ingredients = listOf()),
            Cocktail(name = "Goblet of Fire", image = null,
                description = "",
                recipe = "",
                ingredients = listOf()),
            Cocktail(name = "Purple rain", image = null,
                description = "",
                recipe = "",
                ingredients = listOf()),
            Cocktail(name = "Grapefruit Girl", image = null,
                description = "",
                recipe = "",
                ingredients = listOf()),
            Cocktail(name = "Mojito mocktail", image = null,
                description = "",
                recipe = "",
                ingredients = listOf()),
            Cocktail(name = "Pink Lemonade", image = null,
                description = "",
                recipe = "",
                ingredients = listOf()),
            Cocktail(name = "Mojito mocktail", image = null,
                description = "",
                recipe = "",
                ingredients = listOf()),
            Cocktail(name = "Goblet of Fire", image = null,
                description = "",
                recipe = "",
                ingredients = listOf()),
            Cocktail(name = "Purple rain", image = null,
                description = "",
                recipe = "",
                ingredients = listOf()),
            Cocktail(name = "Grapefruit Girl", image = null,
                description = "",
                recipe = "",
                ingredients = listOf()),
            Cocktail(name = "Mojito mocktail", image = null,
                description = "",
                recipe = "",
                ingredients = listOf()),
        )
    }

    override suspend fun addCocktail(cocktail: Cocktail) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCocktail(cocktail: Cocktail) {
        TODO("Not yet implemented")
    }
}