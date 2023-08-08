package com.arshapshap.surftraineetask.utils.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _error = MutableLiveData<ViewModelError>()
    val error: LiveData<ViewModelError>
        get() = _error

    protected val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading
}