package com.mohaymen.codeassignment.contacts.data.repository

import com.mohaymen.codeassignment.contacts.data.database.ContactEntity
import com.mohaymen.codeassignment.contacts.data.database.ContactsDao
import com.mohaymen.codeassignment.contacts.data.model.LocalContact
import com.mohaymen.codeassignment.contacts.domain.LocalContactsProvider
import com.mohaymen.codeassignment.contacts.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    private val localContactsProvider: LocalContactsProvider,
    private val contactsDao: ContactsDao
) :
    ContactsRepository {

    override fun getLocalContacts(): Flow<List<LocalContact>> =
        localContactsProvider.getContacts()


    override  suspend fun getContactsDb():List<ContactEntity> {
        return contactsDao.getContacts()
    }

    override suspend fun insertContactsDb(contactsList: List<ContactEntity>) {
        return contactsDao.insertContacts(contactsList)
    }

    override suspend fun deleteContactsDb() {
        contactsDao.deleteContacts()
    }


}