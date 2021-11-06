package com.mohaymen.codeassignment.contacts.presentation.main.contact_details


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.mohaymen.codeassignment.contacts.databinding.FragmentContactDetailsBinding
import com.mohaymen.codeassignment.contacts.domain.model.Contact
import com.mohaymen.codeassignment.contacts.presentation.base.BaseBindingFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Show Contact details
 */
@AndroidEntryPoint
class ContactDetailsFragment : BaseBindingFragment<FragmentContactDetailsBinding>() {

    private val args: ContactDetailsFragmentArgs by navArgs()

    private lateinit var contact: Contact

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactDetailsBinding =
        { layoutInflater, viewGroup, b ->
            FragmentContactDetailsBinding.inflate(layoutInflater, viewGroup, b)
        }

    override fun initView() {
        contact = args.contact

        binding.txtContactNameDetails.text = contact.name
        binding.txtContactPhoneNumberDetails.text=contact.phoneNumber

    }


}