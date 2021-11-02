package com.mohaymen.codeassignment.contacts.domain.repository

import com.mohaymen.codeassignment.contacts.data.model.LocalContact


interface ContactsRepository {

    suspend fun getLocalContacts(): List<LocalContact>

}