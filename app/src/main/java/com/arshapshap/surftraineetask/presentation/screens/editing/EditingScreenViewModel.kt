package com.arshapshap.surftraineetask.presentation.screens.editing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arshapshap.surftraineetask.common.base.BaseViewModel
import com.arshapshap.surftraineetask.domain.interactors.CocktailsInteractor
import com.arshapshap.surftraineetask.domain.models.Cocktail
import com.arshapshap.surftraineetask.presentation.navigation.MainRouter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditingScreenViewModel @AssistedInject constructor(
    @Assisted private val cocktailId: Long?,
    private val interactor: CocktailsInteractor,
    private val router: MainRouter
) : BaseViewModel() {

    private val _startCocktailValues = MutableLiveData<Cocktail>()
    val startCocktailValues: LiveData<Cocktail>
        = _startCocktailValues

    init {
        if (cocktailId != null)
            viewModelScope.launch(Dispatchers.IO) {
                interactor.getCocktailById(cocktailId)?.let {
                    _startCocktailValues.postValue(it)
                }
            }
    }

    fun save() {

    }

    fun deleteIngredient(id: Int) {

    }

    fun closeFragment() {
        router.closeCurrentFragment()
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted cocktailId: Long?): EditingScreenViewModel
    }
}