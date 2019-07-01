package com.melih.detail.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.melih.core.actions.EXTRA_LAUNCH_ID
import com.melih.core.base.lifecycle.BaseActivity
import com.melih.detail.R
import com.melih.detail.databinding.DetailActivityBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val INVALID_LAUNCH_ID = -1L

class DetailActivity : BaseActivity<DetailActivityBinding>() {

    // region Functions

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun createNavHostFragment() =
        NavHostFragment.create(
            R.navigation.nav_detail,
            DetailFragmentArgs.Builder()
                .setLaunchId(intent?.extras?.getLong(EXTRA_LAUNCH_ID) ?: INVALID_LAUNCH_ID)
                .build()
                .toBundle()
        )

    override fun addNavHostTo(): Int = R.id.container
    // endregion
}
