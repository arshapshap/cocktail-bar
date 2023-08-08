package com.arshapshap.surftraineetask.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _errorLiveData = MutableLiveData<ViewModelError>()
    val errorLiveData: LiveData<ViewModelError>
        get() = _errorLiveData

    protected val _loadingLiveData = MutableLiveData(true)
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData
}