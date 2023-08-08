package com.arshapshap.surftraineetask.presentation.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arshapshap.surftraineetask.common.base.BaseViewModel
import com.arshapshap.surftraineetask.domain.models.Cocktail
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListViewModel : BaseViewModel() {

    private val _cocktails = MutableLiveData<List<Cocktail>>()
    val cocktails: LiveData<List<Cocktail>>
        get() = _cocktails

    init {
        viewModelScope.launch {
            delay(2000)
            _isLoading.postValue(false)
            _cocktails.postValue(listOf())
        }
    }
}