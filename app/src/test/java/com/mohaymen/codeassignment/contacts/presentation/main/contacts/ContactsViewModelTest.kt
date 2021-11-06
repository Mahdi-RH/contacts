package com.mohaymen.codeassignment.contacts.presentation.main.contacts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.mohaymen.codeassignment.contacts.domain.repository.FakeRepository
import com.mohaymen.codeassignment.contacts.domain.usecase.GetContactsUseCase
import com.mohaymen.codeassignment.contacts.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class ContactsViewModelTest{


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule: CoroutinesTestRule = CoroutinesTestRule()

    private lateinit var fakeGetGamesUseCase: GetContactsUseCase
    private lateinit var fakeContactsRepository: FakeRepository
    private lateinit var contactsViewModel: ContactsViewModel


    @Before
    fun setUp() {
        fakeContactsRepository = FakeRepository()
        fakeGetGamesUseCase = GetContactsUseCase(fakeContactsRepository, testCoroutineRule.testDispatcher)
        contactsViewModel = ContactsViewModel(fakeGetGamesUseCase)
    }


    @Test
    fun `get contacts return contacts list`() = runBlockingTest {

        fakeContactsRepository.setListSize(10)
        fakeContactsRepository.hasException(false)
        contactsViewModel.getContacts()
        Truth.assertThat(contactsViewModel.contacts.value is ContactsScreenState).isTrue()
        Truth.assertThat(contactsViewModel.contacts.value?.error).isEmpty()
        Truth.assertThat(contactsViewModel.contacts.value?.contactsList)
            .isEqualTo(fakeContactsRepository.getContactsList())

    }

    @Test
    fun `get contacts return error`() = runBlockingTest {

        fakeContactsRepository.hasException(true)
        contactsViewModel.getContacts()
        Truth.assertThat(contactsViewModel.contacts.value is ContactsScreenState).isTrue()
        Truth.assertThat(contactsViewModel.contacts.value?.error).isNotEmpty()
    }


}