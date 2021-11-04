package com.mohaymen.codeassignment.contacts.domain.repository

import com.mohaymen.codeassignment.contacts.data.database.ContactEntity
import com.mohaymen.codeassignment.contacts.data.model.LocalContact
import kotlinx.coroutines.flow.Flow


interface ContactsRepository {

    fun getLocalContacts(): Flow<List<LocalContact>>

    suspend fun getContactsDb(): List<ContactEntity>

    suspend fun insertContactsDb(contactsList: List<ContactEntity>)

    suspend fun deleteContactsDb()

}