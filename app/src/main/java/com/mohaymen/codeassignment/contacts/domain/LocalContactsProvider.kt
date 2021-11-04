package com.mohaymen.codeassignment.contacts.domain

import com.mohaymen.codeassignment.contacts.data.model.LocalContact
import kotlinx.coroutines.flow.Flow

interface LocalContactsProvider {

     fun getContacts(): Flow<List<LocalContact>>
}