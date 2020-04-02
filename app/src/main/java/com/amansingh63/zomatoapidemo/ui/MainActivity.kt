package com.amansingh63.zomatoapidemo.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.amansingh63.zomatoapidemo.R
import com.amansingh63.zomatoapidemo.databinding.ActivityMainBinding
import com.amansingh63.zomatoapidemo.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun layoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        appBarConfiguration = AppBarConfiguration.Builder(R.id.searchFragment).build()
        setupActionBarWithNavController(
            findNavController(R.id.nav_host_fragment),
            appBarConfiguration
        )
    }


}
