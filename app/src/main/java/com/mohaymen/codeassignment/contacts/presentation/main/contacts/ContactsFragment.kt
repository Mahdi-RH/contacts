package com.mohaymen.codeassignment.contacts.presentation.main.contacts


import android.Manifest
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mohaymen.codeassignment.contacts.R
import com.mohaymen.codeassignment.contacts.databinding.FragmentContactsBinding
import com.mohaymen.codeassignment.contacts.domain.model.Contact
import com.mohaymen.codeassignment.contacts.presentation.base.BaseBindingFragment
import com.mohaymen.codeassignment.contacts.presentation.main.MainActivity
import com.mohaymen.codeassignment.contacts.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsFragment : BaseBindingFragment<FragmentContactsBinding>() {


    private val viewModel: ContactsViewModel by viewModels()

    private val adapter: ContactsListAdapter by lazy {
        ContactsListAdapter { contact ->
            goToContactDetails(contact)
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactsBinding =
        { layoutInflater, viewGroup, b ->
            FragmentContactsBinding.inflate(layoutInflater, viewGroup, b)
        }

    private fun goToContactDetails(contact: Contact) {
        val action =
            ContactsFragmentDirections.actionContactsFragmentToContactDetailsFragment(contact)
        findNavController().navigate(
            action
        )
    }

    override fun initView() {
        binding.recyclerContacts.adapter = adapter

        storagePermissionCheck()

        viewModel.contacts.observe(viewLifecycleOwner, { screenState ->
            when {
                screenState.isLoading -> { // loading handling
                    (activity as MainActivity).displayProgress(true)
                }
                screenState.contactsList.isNotEmpty() -> {  // data handling
                    (activity as MainActivity).displayProgress(false)
                    adapter.submitList(screenState.contactsList)
                }
                screenState.error != "" -> {  // error handling
                    (activity as MainActivity).displayProgress(false)
                    displayMessage(screenState.error)


                }
                screenState.error == "" && screenState.contactsList.isEmpty()-> {  // no contacts handling
                    (activity as MainActivity).displayProgress(false)
                    displayMessage(requireContext().getString(R.string.no_contact))


                }
            }

        })


    }

    private fun getContacts() {
        viewModel.getContacts()

    }

    private fun storagePermissionCheck() {
        if (checkSelfPermission()) {
            getContacts()
        } else
            requestPermission()
    }

    private fun checkSelfPermission(): Boolean {
        val result = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_CONTACTS
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.READ_CONTACTS),
            Constants.READ_CONTACTS_REQUEST_CODE
        )

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            Constants.READ_CONTACTS_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getContacts()

                } else {
                    // disable function did not granted
                    displayMessage("Need Permission to Access Constants")

                }
            }
            else -> return
        }

    }


}