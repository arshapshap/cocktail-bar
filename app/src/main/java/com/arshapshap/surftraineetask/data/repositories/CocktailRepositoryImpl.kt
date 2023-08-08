package com.arshapshap.surftraineetask.data.repositories

import com.arshapshap.surftraineetask.data.dao.CocktailDao
import com.arshapshap.surftraineetask.data.mappers.CocktailMapper
import com.arshapshap.surftraineetask.domain.models.Cocktail
import com.arshapshap.surftraineetask.domain.repositories.CocktailRepository
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val localSource: CocktailDao,
    private val mapper: CocktailMapper
) : CocktailRepository {

    override suspend fun getCocktails(): List<Cocktail> {
        return localSource.getAll().map { mapper.mapToDomain(it) }
    }

    override suspend fun getCocktailById(id: Long): Cocktail? {
        return localSource.getById(id)?.let { mapper.mapToDomain(it) }
    }

    override suspend fun addCocktail(cocktail: Cocktail): Long {
        return localSource.add(mapper.mapToEntity(cocktail))
    }

    override suspend fun updateCocktail(cocktail: Cocktail) {
        localSource.update(mapper.mapToEntity(cocktail))
    }
}