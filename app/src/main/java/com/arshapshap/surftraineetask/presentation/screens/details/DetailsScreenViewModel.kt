package com.arshapshap.surftraineetask.presentation.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arshapshap.surftraineetask.R
import com.arshapshap.surftraineetask.common.base.BaseViewModel
import com.arshapshap.surftraineetask.common.base.ViewModelError
import com.arshapshap.surftraineetask.common.base.ViewModelErrorLevel
import com.arshapshap.surftraineetask.domain.interactors.CocktailsInteractor
import com.arshapshap.surftraineetask.domain.models.Cocktail
import com.arshapshap.surftraineetask.presentation.navigation.MainRouter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsScreenViewModel @AssistedInject constructor(
    @Assisted private val cocktailId: Long?,
    private val interactor: CocktailsInteractor,
    private val router: MainRouter
) : BaseViewModel() {

    private val _cocktail = MutableLiveData<Cocktail>()
    val cocktail: LiveData<Cocktail>
        = _cocktail

    init {
        if (cocktailId == null)
            _error.postValue(ViewModelError(
                messageRes = R.string.cocktail_not_found,
                level = ViewModelErrorLevel.Error
            ))
    }

    fun loadDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val loadedCocktail = interactor.getCocktailById(id = cocktailId!!)
            if (loadedCocktail == null) {
                _error.postValue(ViewModelError(
                    messageRes = R.string.cocktail_not_found,
                    level = ViewModelErrorLevel.Error
                ))
                return@launch
            }

            _isLoading.postValue(false)
            _cocktail.postValue(loadedCocktail!!)
        }
    }

    fun closeFragment() {
        router.closeCurrentFragment()
    }

    fun editCocktail() {
        router.openCocktailEditing(cocktailId!!)
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted cocktailId: Long?): DetailsScreenViewModel
    }
}