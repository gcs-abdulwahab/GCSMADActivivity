package com.example.gcsmadactivivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import com.example.gcsmadactivivity.viewmodels.ContactListViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import models.Contact

class MyViewModelActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {
                ColorChanger()
            }
        }
    }
}

class ColorViewModel : ViewModel() {
    private val _colors = listOf(Color.Red, Color.Green, Color.Yellow, Color.Magenta, Color.Gray)

    private val _selectcolor = MutableStateFlow(_colors[0])
    val selectColor = _selectcolor.asStateFlow()

    var composeColor by mutableStateOf(_colors.random())
        private set

    fun generateNewColor() {
        val color = _colors.random()
        composeColor = color
        _selectcolor.value = color
    }
}


@Composable
fun ColorChanger() {

    val viewModel: ColorViewModel = viewModel()
    val composeColor = viewModel.composeColor
    val flowColor by viewModel.selectColor.collectAsState()

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .background(flowColor)
            .fillMaxSize()
            .clickable {
                //viewModel.generateNewColor()


                Intent(context, BalanceActivity::class.java).also {
                    context.startActivity(it)
                }


            },

        )

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






