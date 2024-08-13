package com.example.gcsmadactivivity.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import models.Contact

class ContactListViewModel : ViewModel() {

    var contacts = MutableStateFlow(Contact.contacts)


    fun addContact(contact: Contact) {
        contacts.value += contact
    }

}