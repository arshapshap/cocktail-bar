package com.arshapshap.surftraineetask.presentation.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arshapshap.surftraineetask.common.base.BaseViewModel
import com.arshapshap.surftraineetask.domain.interactors.ListScreenInteractor
import com.arshapshap.surftraineetask.domain.models.Cocktail
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class ListScreenViewModel @AssistedInject constructor(
    private val interactor: ListScreenInteractor
) : BaseViewModel() {

    private val _cocktails = MutableLiveData<List<Cocktail>>()
    val cocktails: LiveData<List<Cocktail>>
        get() = _cocktails

    fun loadCocktails() {
        viewModelScope.launch {
            val list = interactor.getCocktails()
            _isLoading.postValue(false)
            _cocktails.postValue(list)
        }
    }

    @AssistedFactory
    interface Factory {

        fun create(): ListScreenViewModel
    }
}