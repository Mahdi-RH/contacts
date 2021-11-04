package com.mohaymen.codeassignment.contacts.data.database

import androidx.room.*


@Dao
interface ContactsDao {


    @Query("SELECT * FROM contacts")
      fun getContacts(): List<ContactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contacts: List<ContactEntity>)

    @Query("DELETE FROM contacts")
    suspend fun deleteContacts()

}