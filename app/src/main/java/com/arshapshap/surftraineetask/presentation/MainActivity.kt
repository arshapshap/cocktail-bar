package com.arshapshap.surftraineetask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.arshapshap.surftraineetask.R
import com.arshapshap.surftraineetask.utils.di.appComponent
import com.arshapshap.surftraineetask.presentation.navigation.Navigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()

        navController =
            (supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment).navController

        navController?.let {
            navigator.attachNavController(it, R.navigation.nav_graph)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navController?.let {
            navigator.detachNavController(it)
        }
        navController = null
    }

    private fun inject() {
        applicationContext.appComponent().inject(this)
    }
}