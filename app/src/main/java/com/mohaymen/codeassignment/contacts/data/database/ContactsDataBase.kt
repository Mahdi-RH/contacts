package com.mohaymen.codeassignment.contacts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactEntity::class], version = 1)
abstract class ContactsDataBase : RoomDatabase() {
    abstract fun getDao(): ContactsDao
}