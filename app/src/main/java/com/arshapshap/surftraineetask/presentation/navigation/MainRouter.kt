package com.arshapshap.surftraineetask.presentation.navigation

interface MainRouter {

    fun openCocktailDetails(id: Long)

    fun openCocktailEditing(id: Long)

    fun openCocktailCreating()

    fun closeCurrentFragment()
}