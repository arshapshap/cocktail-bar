package com.arshapshap.surftraineetask.data.mappers

import com.arshapshap.surftraineetask.data.models.CocktailEntity
import com.arshapshap.surftraineetask.domain.models.Cocktail
import javax.inject.Inject

class CocktailMapper @Inject constructor() {

    fun mapToEntity(cocktail: Cocktail): CocktailEntity = with(cocktail) {
        CocktailEntity(
            id = id,
            name = name,
            image = image,
            description = description,
            recipe = recipe,
            ingredients = ingredients
        )
    }

    fun mapToDomain(cocktailEntity: CocktailEntity): Cocktail = with(cocktailEntity) {
        Cocktail(
            id = id,
            name = name,
            image = image,
            description = description,
            recipe = recipe,
            ingredients = ingredients
        )
    }
}