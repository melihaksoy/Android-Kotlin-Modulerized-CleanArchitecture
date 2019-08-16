package com.melih.rocketscience

import android.content.IntentFilter
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.melih.rocketscience.databinding.MainActivityBinding
import com.melih.rocketscience.navigation.NavigationHandler
import com.melih.rocketscience.navigation.NavigationReceiver
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), NavigationHandler {

    private val navigationReceiver = NavigationReceiver(this)

    private lateinit var binding: MainActivityBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(binding.toolbar, navController)

        //TODO remove static string
        LocalBroadcastManager
            .getInstance(this)
            .registerReceiver(
                navigationReceiver,
                IntentFilter("action.detail.open")
            )
    }

    override fun onDestroy() {
        super.onDestroy()

        LocalBroadcastManager
            .getInstance(this)
            .unregisterReceiver(navigationReceiver)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!NavigationUI.navigateUp(navController, null)) {
            onBackPressed()
        }

        return true
    }

    override fun navigate(directions: NavDirections) {
        navController.navigate(directions)
    }
}
