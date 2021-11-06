package com.mohaymen.codeassignment.contacts.data

import android.content.ContentResolver
import android.net.Uri
import android.provider.ContactsContract
import app.cash.copper.flow.mapToList
import app.cash.copper.flow.observeQuery
import com.mohaymen.codeassignment.contacts.data.model.LocalContact
import com.mohaymen.codeassignment.contacts.domain.LocalContactsProvider
import javax.inject.Inject


class LocalContactsProviderImpl @Inject constructor(
    private val contentResolver: ContentResolver
) :
    LocalContactsProvider {

    private val uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
    private val columns = arrayOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    )

    override fun getContacts() = contentResolver.observeQuery(
        uri = uri,
        projection = columns,
        sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
    ).mapToList { cursor ->
        val id =
            cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID))
        val name =
            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
        val number =
            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
        LocalContact(id = id, name = name, phoneNumber = number)
    }


}