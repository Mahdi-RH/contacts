package com.mohaymen.codeassignment.contacts.presentation.main

import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mohaymen.codeassignment.contacts.R
import com.mohaymen.codeassignment.contacts.databinding.ActivityMainBinding
import com.mohaymen.codeassignment.contacts.presentation.base.BaseBindingActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    lateinit var navHostFragment: NavHostFragment

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding = { layoutInflater ->
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {

        navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragment.id) as NavHostFragment

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.contactsFragment,
                R.id.contactDetailsFragment
            )
        )

        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)


    }

}