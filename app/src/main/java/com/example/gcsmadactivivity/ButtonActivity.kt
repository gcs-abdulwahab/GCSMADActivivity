package com.example.gcsmadactivivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gcsmadactivivity.models.Contact
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme

class ButtonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {

               val contacts = Contact.contacts
                LazyColumn {
                    items(contacts) {
                        VisitingCard(contact = it)
                    }
                }


            }
        }
    }
}


@Composable
fun VisitingCard(contact: Contact, modifier: Modifier = Modifier) {

    var isClicked by remember { mutableStateOf(true) }


    ElevatedCard(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(all = 4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Picture", modifier = Modifier.size(100.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "ID: ${contact.id}")
                Text(text = "Hello ${contact.name}  !")
            }
            Spacer(modifier = Modifier.weight(1f))
            ElevatedButton(onClick = {

                Log.d("ButtonActivity", "Button Clicked")
                isClicked = !isClicked

            }) {

                if (isClicked) {
                    Text(text = "Click Me")
                } else {
                    Text(text = "Clicked")
                }

            }

        }


    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Light Mode")
@Composable
fun VisitingCardPreview() {
    GCSMADActivivityTheme {
        VisitingCard(contact = Contact("Default", 1))

    }
}





