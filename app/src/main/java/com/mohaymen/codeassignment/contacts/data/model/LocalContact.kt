package com.mohaymen.codeassignment.contacts.data.model

import com.mohaymen.codeassignment.contacts.data.database.ContactEntity

data class LocalContact(val id: Int, val name: String, val phoneNumber: String)

fun LocalContact.toContactEntity(): ContactEntity = ContactEntity(
    id = id, name = name, phoneNumber = phoneNumber
)