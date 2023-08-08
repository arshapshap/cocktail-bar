package com.arshapshap.surftraineetask.domain.interactors

import com.arshapshap.surftraineetask.domain.models.Cocktail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListScreenInteractor @Inject constructor() {

    suspend fun getCocktails(): List<Cocktail> {
        return listOf(
            Cocktail("Pink Lemonade", null),
            Cocktail("Mojito mocktail", null),
            Cocktail("Goblet of Fire", null),
            Cocktail("Purple rain", null),
            Cocktail("Grapefruit Girl", null),
            Cocktail("Mojito mocktail", null),
            Cocktail("Pink Lemonade", null),
            Cocktail("Mojito mocktail", null),
            Cocktail("Goblet of Fire", null),
            Cocktail("Purple rain", null),
            Cocktail("Grapefruit Girl", null),
            Cocktail("Mojito mocktail", null),
        )
    }
}