package com.mohaymen.codeassignment.contacts.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mohaymen.codeassignment.contacts.domain.model.Contact

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "phone_number") val phoneNumber: String
)

fun ContactEntity.toContact() = Contact(
    id = id, name = name, phoneNumber = phoneNumber
)