package com.mohaymen.codeassignment.contacts.data.model

import com.mohaymen.codeassignment.contacts.domain.model.Contact

data class LocalContact(val id: Int, val name: String, val phoneNumber: String)

fun LocalContact.toContact(): Contact = Contact(
    id = id, name = name, phoneNumber = phoneNumber
)