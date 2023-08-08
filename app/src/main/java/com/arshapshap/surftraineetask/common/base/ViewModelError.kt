package com.arshapshap.surftraineetask.common.base

import androidx.annotation.StringRes

data class ViewModelError(
    @StringRes val messageRes: Int,
    val level: ViewModelErrorLevel
)

enum class ViewModelErrorLevel {
    Error,
    Message
}