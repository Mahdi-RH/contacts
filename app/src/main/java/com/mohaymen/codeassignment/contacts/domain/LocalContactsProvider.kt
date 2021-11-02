package com.mohaymen.codeassignment.contacts.domain

import com.mohaymen.codeassignment.contacts.data.model.LocalContact

interface LocalContactsProvider {

   suspend fun getContacts(): List<LocalContact>
}