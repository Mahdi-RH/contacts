package com.mohaymen.codeassignment.contacts.presentation.main.contact_details


import android.view.LayoutInflater
import android.view.ViewGroup
import com.mohaymen.codeassignment.contacts.databinding.FragmentContactDetailsBinding
import com.mohaymen.codeassignment.contacts.presentation.base.BaseBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactDetailsFragment : BaseBindingFragment<FragmentContactDetailsBinding>() {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactDetailsBinding =
        { layoutInflater, viewGroup, b ->
            FragmentContactDetailsBinding.inflate(layoutInflater, viewGroup, b)
        }

    override fun initView() {

    }


}