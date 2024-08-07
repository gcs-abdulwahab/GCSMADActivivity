package com.example.gcsmadactivivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import com.example.gcsmadactivivity.viewmodels.ContactListViewModel
import models.Contact

class MyViewModelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {
                MyViewModelScreen()
            }
        }
    }
}


@Composable
fun MyViewModelScreen() {

    val viewModel: ContactListViewModel = viewModel()
    val contacts by viewModel.contacts.collectAsState()

//    val contacts = Contact.contacts

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            Button(onClick = {
                // add a new contact in contacts
                viewModel.addContact(Contact(20, "New Contact"))


            }) {
                Text(text = "ADD")
            }

            LazyColumn {
                // create items
                items(contacts) {
                    // add key
                    key(it.id) {
                        Text(text = " ${it.name}")
                        HorizontalDivider()
                    }
                }
            }

        }
    }

}






