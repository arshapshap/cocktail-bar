package com.arshapshap.surftraineetask.presentation.screens.editing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arshapshap.surftraineetask.common.base.BaseViewModel
import com.arshapshap.surftraineetask.domain.interactors.CocktailsInteractor
import com.arshapshap.surftraineetask.domain.models.Cocktail
import com.arshapshap.surftraineetask.presentation.navigation.MainRouter
import com.arshapshap.surftraineetask.presentation.screens.editing.data.CocktailInputError
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditingScreenViewModel @AssistedInject constructor(
    @Assisted private val cocktailId: Long?,
    private val interactor: CocktailsInteractor,
    private val router: MainRouter
) : BaseViewModel() {

    private val _startCocktailValues = MutableLiveData<Cocktail>()
    val startCocktailValues: LiveData<Cocktail>
        = _startCocktailValues

    private val _editingCocktail = MutableLiveData<Cocktail>()
    val editingCocktail: LiveData<Cocktail>
        = _editingCocktail

    private val _inputErrors = MutableLiveData<List<CocktailInputError>>()
    val inputErrors: LiveData<List<CocktailInputError>>
        = _inputErrors

    init {
        if (cocktailId != null)
            viewModelScope.launch(Dispatchers.IO) {
                interactor.getCocktailById(cocktailId)?.let {
                    _startCocktailValues.postValue(it)
                    _editingCocktail.postValue(it)
                }
            }
        else
            _editingCocktail.postValue(
                Cocktail(
                    id = 0,
                    image = "",
                    name = "",
                    description = "",
                    recipe = "",
                    ingredients = listOf()
                )
            )
    }

    fun changeName(name: String) {
        editingCocktail.value?.let {
            _editingCocktail.postValue(
                it.copy(name = name)
            )
        }
    }

    fun changeDescription(description: String) {
        editingCocktail.value?.let {
            _editingCocktail.postValue(
                it.copy(description = description)
            )
        }
    }

    fun addIngredient(ingredient: String) {
        editingCocktail.value?.let {
            if (ingredient in it.ingredients) return

            _editingCocktail.postValue(
                it.copy(ingredients = it.ingredients.plus(ingredient))
            )
        }
    }

    fun deleteIngredient(ingredient: String) {
        editingCocktail.value?.let {
            _editingCocktail.postValue(
                it.copy(ingredients = it.ingredients.remove(ingredient))
            )
        }
    }

    fun changeRecipe(recipe: String) {
        editingCocktail.value?.let {
            _editingCocktail.postValue(
                it.copy(recipe = recipe)
            )
        }
    }

    fun save() {
        editingCocktail.value?.let {
            val errors = mutableListOf<CocktailInputError>()
            if (it.name.isBlank()) {
                errors.add(CocktailInputError.EmptyTitle)
            }
            if (it.ingredients.isEmpty()) {
                errors.add(CocktailInputError.NoIngredients)
            }

            if (errors.isNotEmpty()) {
                _inputErrors.postValue(errors)
                return
            }

            viewModelScope.launch(Dispatchers.IO) {
                if (cocktailId == null) {
                    val id = interactor.addCocktail(it)
                    withContext(Dispatchers.Main) {
                        router.openCocktailsListWithScroll(id)
                    }
                } else {
                    interactor.updateCocktail(it)
                    withContext(Dispatchers.Main) {
                        router.closeCurrentFragment()
                    }
                }
            }
        }
    }

    fun closeFragment() {
        router.closeCurrentFragment()
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted cocktailId: Long?): EditingScreenViewModel
    }

    private fun List<String>.remove(string: String): List<String> {
        val result = this.toMutableList()
        result.remove(string)
        return result
    }
}