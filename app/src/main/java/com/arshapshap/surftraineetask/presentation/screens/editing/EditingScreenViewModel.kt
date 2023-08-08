package com.arshapshap.surftraineetask.presentation.screens.editing

import com.arshapshap.surftraineetask.common.base.BaseViewModel
import com.arshapshap.surftraineetask.presentation.navigation.MainRouter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class EditingScreenViewModel @AssistedInject constructor(
    @Assisted private val cocktailId: Long?,
    private val router: MainRouter
) : BaseViewModel() {

    fun closeFragment() {
        router.closeCurrentFragment()
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted cocktailId: Long?): EditingScreenViewModel
    }}