package com.mohaymen.codeassignment.contacts.domain.usecase

import com.mohaymen.codeassignment.contacts.data.database.toContact
import com.mohaymen.codeassignment.contacts.data.model.toContactEntity
import com.mohaymen.codeassignment.contacts.domain.model.Contact
import com.mohaymen.codeassignment.contacts.domain.repository.ContactsRepository
import com.mohaymen.codeassignment.contacts.utils.Constants
import com.mohaymen.codeassignment.contacts.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class GetContactsUseCase @Inject constructor(
    private val repository: ContactsRepository,
    @Named(Constants.IO_DISPATCHER) private val dispatcher: CoroutineDispatcher
) {


    operator fun invoke() = flow<Resource<List<Contact>>> {
        try {
            repository.getLocalContacts().collect { localContacts ->
                emit(Resource.Loading())
                val contactsEntity =
                    localContacts.map { localContact ->  // convert contacts to desired one for database
                        localContact.toContactEntity()
                    }
                repository.deleteContactsDb()   // delete old contacts from database
                repository.insertContactsDb(contactsEntity) // insert fresh contacts to database
                val contacts = repository.getContactsDb()
                    .map { contactEntity -> // convert database contacts to desired one for ui layer
                        contactEntity.toContact()
                    }
                emit(Resource.Success(contacts))
            }


        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage))
        }


    }.flowOn(dispatcher)


}