package com.mohaymen.codeassignment.contacts.presentation.main.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaymen.codeassignment.contacts.domain.usecase.GetContactsUseCase
import com.mohaymen.codeassignment.contacts.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(private val getContactsUseCase: GetContactsUseCase) :
    ViewModel() {

    private val _contacts = MutableLiveData<ContactsScreenState>()
    val contacts: LiveData<ContactsScreenState> = _contacts

    fun getContacts() {

        getContactsUseCase().onEach { result ->
            when (result) {

                is Resource.Loading -> {
                    _contacts.value = ContactsScreenState(isLoading = true)

                }
                is Resource.Success -> {
                    _contacts.value = ContactsScreenState(contactsList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _contacts.value = ContactsScreenState(
                        error = result.message ?: "an unexpected error happened"
                    )

                }


            }
        }.launchIn(viewModelScope)
    }


}