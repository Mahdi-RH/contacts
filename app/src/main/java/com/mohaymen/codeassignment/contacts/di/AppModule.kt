package com.mohaymen.codeassignment.contacts.di

import android.content.ContentResolver
import android.content.Context
import androidx.room.Room
import com.mohaymen.codeassignment.contacts.data.LocalContactsProviderImpl
import com.mohaymen.codeassignment.contacts.data.database.ContactsDao
import com.mohaymen.codeassignment.contacts.data.database.ContactsDataBase
import com.mohaymen.codeassignment.contacts.data.repository.ContactsRepositoryImpl
import com.mohaymen.codeassignment.contacts.domain.LocalContactsProvider
import com.mohaymen.codeassignment.contacts.domain.repository.ContactsRepository
import com.mohaymen.codeassignment.contacts.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideContentResolver(@ApplicationContext context: Context): ContentResolver =
        context.contentResolver

    @Singleton
    @Provides
    fun provideLocalContactsProvider(contentResolver: ContentResolver): LocalContactsProvider =
        LocalContactsProviderImpl(contentResolver)




    @Singleton
    @Provides
    fun provideContactsRepository(
        localContactsProvider: LocalContactsProvider,
        contactsDao: ContactsDao
    ): ContactsRepository =
        ContactsRepositoryImpl(localContactsProvider, contactsDao)

    @Named(Constants.IO_DISPATCHER)
    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Named(Constants.MAIN_DISPATCHER)
    @Singleton
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Singleton
    @Provides
    fun provideContactsDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ContactsDataBase::class.java,
            Constants.CONTACTS_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideContactsDao(contactsDataBase: ContactsDataBase): ContactsDao =
        contactsDataBase.getDao()

}