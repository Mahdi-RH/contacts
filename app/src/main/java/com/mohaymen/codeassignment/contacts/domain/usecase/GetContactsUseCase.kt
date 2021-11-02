package com.mohaymen.codeassignment.contacts.domain.usecase

import com.mohaymen.codeassignment.contacts.data.model.toContact
import com.mohaymen.codeassignment.contacts.domain.model.Contact
import com.mohaymen.codeassignment.contacts.domain.repository.ContactsRepository
import com.mohaymen.codeassignment.contacts.utils.Constants
import com.mohaymen.codeassignment.contacts.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class GetContactsUseCase @Inject constructor(
    private val repository: ContactsRepository,
    @Named(Constants.IO_DISPATCHER) private val dispatcher: CoroutineDispatcher
) {


    operator fun invoke() = flow<Resource<List<Contact>>> {
        emit(Resource.Loading())
        try {
            val localContacts = repository.getLocalContacts()
            emit(Resource.Success(data = localContacts.map { localContact ->
                localContact.toContact()

            }))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage))
        }


    }.flowOn(dispatcher)

}