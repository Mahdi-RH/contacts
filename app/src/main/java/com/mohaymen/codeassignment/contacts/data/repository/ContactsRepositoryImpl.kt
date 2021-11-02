package com.mohaymen.codeassignment.contacts.data.repository

import com.mohaymen.codeassignment.contacts.data.model.LocalContact
import com.mohaymen.codeassignment.contacts.domain.LocalContactsProvider
import com.mohaymen.codeassignment.contacts.domain.repository.ContactsRepository
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(private val localContactsProvider: LocalContactsProvider) :
    ContactsRepository {
    override suspend fun getLocalContacts(): List<LocalContact> =
        localContactsProvider.getContacts()
}