package com.melih.list.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.melih.core.base.lifecycle.BaseActivity
import com.melih.list.R
import com.melih.list.databinding.LaunchesActivityBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

class LaunchesActivity : BaseActivity<LaunchesActivityBinding>() {

    // region Functions

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
    }

    override fun getLayoutId(): Int = R.layout.activity_launches

    override fun createNavHostFragment() =
        NavHostFragment.create(R.navigation.nav_launches)

    override fun addNavHostTo(): Int = R.id.container
    // endregion
}
