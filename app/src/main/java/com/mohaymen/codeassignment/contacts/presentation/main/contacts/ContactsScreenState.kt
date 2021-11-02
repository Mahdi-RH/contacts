package com.mohaymen.codeassignment.contacts.presentation.main.contacts

import com.mohaymen.codeassignment.contacts.domain.model.Contact

data class ContactsScreenState(
    val isLoading: Boolean = false,
    val contactsList: List<Contact> = emptyList(),
    val error: String = ""
)