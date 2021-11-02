package com.mohaymen.codeassignment.contacts.data

import android.content.ContentResolver
import android.net.Uri
import android.provider.ContactsContract
import com.mohaymen.codeassignment.contacts.data.model.LocalContact
import com.mohaymen.codeassignment.contacts.domain.LocalContactsProvider
import javax.inject.Inject


class LocalContactsProviderImpl @Inject constructor(
    private val contentResolver: ContentResolver,
) :
    LocalContactsProvider {

    private val uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
    private val columns = arrayOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    )


    override suspend fun getContacts(): List<LocalContact> {

        val contactsList = ArrayList<LocalContact>()

        val cursor = contentResolver.query(
            uri,
            columns, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )

        cursor?.let {
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val number =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                    contactsList.add(LocalContact(id = id, name = name, phoneNumber = number))

                }

            }
        }


        return contactsList
    }
}