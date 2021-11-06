package com.mohaymen.codeassignment.contacts.domain.repository

import android.database.sqlite.SQLiteException
import com.mohaymen.codeassignment.contacts.data.database.ContactEntity
import com.mohaymen.codeassignment.contacts.data.model.LocalContact
import com.mohaymen.codeassignment.contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.lang.Exception

class FakeRepository : ContactsRepository {

    private val localContactsList = ArrayList<LocalContact>()
    private val contactsListDb = ArrayList<ContactEntity>()
    private var size: Int = 0 // size of games list
    private var hasException = false // simulating Exception situation


    fun hasException(exception: Boolean) {
        hasException = exception
    }


    fun setListSize(size: Int) {
        this.size = size
    }


    @Throws(Exception::class)
    override fun getLocalContacts(): Flow<List<LocalContact>> {
        return if (size > 0 && !hasException) flowOf(createLocalContactsList())
        else if (hasException) throw Exception("an unexpected error happened")
        else flowOf(emptyList())
    }


    @Throws(SQLiteException::class)
    override suspend fun getContactsDb(): List<ContactEntity> {
        return if (hasException) throw SQLiteException("unexpected error happened ")
        else
            createContactsListDb()
    }

    @Throws(SQLiteException::class)
    override suspend fun insertContactsDb(contactsList: List<ContactEntity>) { // if it has an exception, throw it. Otherwise do nothing(it means contacts were added successfully).
        if (hasException) throw SQLiteException("unexpected error happened ")
    }

    @Throws(SQLiteException::class)
    override suspend fun deleteContactsDb() { // if it has an exception throw it, otherwise do nothing(it means contacts were deleted successfully).
        if (hasException) throw SQLiteException("unexpected error happened ")
    }


    fun getLocalContactsList() = localContactsList

    fun getContactsList() = localContactsList.map { localContact ->
        Contact(localContact.id, localContact.name, localContact.phoneNumber)

    }

    // create a fake localContactsList
    private fun createLocalContactsList(): List<LocalContact> {
        localContactsList.clear()
        for (i: Int in 1 until size) {
            (localContactsList).add(
                LocalContact(i, "", "")
            )
        }
        return localContactsList
    }

    // create a fake database response
    private fun createContactsListDb(): List<ContactEntity> {
        contactsListDb.clear()
        for (i: Int in 1 until size) {
            contactsListDb.add(
                ContactEntity(i, "", "")
            )
        }
        return contactsListDb
    }
}