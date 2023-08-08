package com.arshapshap.surftraineetask.presentation.navigation

import androidx.navigation.NavController
import com.arshapshap.surftraineetask.R
import com.arshapshap.surftraineetask.presentation.screens.details.DetailsFragment

class Navigator : MainRouter {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController, graph: Int) {
        navController.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    override fun openCocktailDetails(id: Long) {
        navController?.navigate(R.id.action_listFragment_to_detailsFragment, DetailsFragment.createBundle(id))
    }

    override fun openCocktailEditing(id: Long) {
        navController?.navigate(R.id.action_detailsFragment_to_editingFragment, DetailsFragment.createBundle(id))
    }

    override fun openCocktailCreating() {
        navController?.navigate(R.id.action_listFragment_to_editingFragment)
    }

    override fun closeCurrentFragment() {
        navController?.popBackStack()
    }
}