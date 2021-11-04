package com.mohaymen.codeassignment.contacts.presentation.main

import android.view.LayoutInflater
import com.mohaymen.codeassignment.contacts.databinding.ActivityMainBinding
import com.mohaymen.codeassignment.contacts.presentation.base.BaseBindingActivity
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding = { layoutInflater ->
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {


    }

}