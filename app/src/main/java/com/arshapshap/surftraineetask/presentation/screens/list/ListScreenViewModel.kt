package com.arshapshap.surftraineetask.presentation.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arshapshap.surftraineetask.common.base.BaseViewModel
import com.arshapshap.surftraineetask.domain.interactors.CocktailsInteractor
import com.arshapshap.surftraineetask.domain.models.Cocktail
import com.arshapshap.surftraineetask.presentation.navigation.MainRouter
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListScreenViewModel @AssistedInject constructor(
    private val interactor: CocktailsInteractor,
    private val router: MainRouter
) : BaseViewModel() {

    private val _cocktails = MutableLiveData<List<Cocktail>>()
    val cocktails: LiveData<List<Cocktail>>
        get() = _cocktails

    fun loadCocktails() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = interactor.getCocktails()
            _isLoading.postValue(false)
            _cocktails.postValue(list)
        }
    }

    fun openCocktailDetails(id: Long) {
        router.openCocktailDetails(id)
    }

    fun createCocktail() {
        router.openCocktailCreating()
    }

    @AssistedFactory
    interface Factory {

        fun create(): ListScreenViewModel
    }
}