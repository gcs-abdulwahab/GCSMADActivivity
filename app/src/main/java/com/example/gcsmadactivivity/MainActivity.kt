package com.example.gcsmadactivivity

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import models.Contact

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        setContent {
            GCSMADActivivityTheme {
                Scaffold(
                    contentColor = MaterialTheme.colorScheme.primary,
                    content = {
                        val contacts = Contact.contacts
                        LazyColumn {
                            items(contacts) { contact ->
                                ContactItem(contact = contact)
                            }

                        }


                    }
                )


            }
        }
    }
}


@Composable
fun ContactItem(contact: Contact) {


    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Red)
            .fillMaxWidth(),
        verticalAlignment = CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Contact Image"
        )

        Column {
            Text(text = contact.id.toString(), style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = contact.name, style = MaterialTheme.typography.titleLarge)
        }
//       Add Button for detail and this should be on the right of the row
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {

                Log.d("Contact Detail", "Contact Detail Clicked")


            }, modifier = Modifier
                .width(100.dp)
                .height(50.dp)
        ) {
            Text(text = "Detail")
        }
    }


}

@Preview
@Composable
fun ContactItemPreview() {


    ContactItem(contact = Contact(1, "Default", imageUrl = "https://picsum.photos/200/300"))


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    GCSMADActivivityTheme {
        val contacts = Contact.contacts
        LazyColumn {
            items(contacts.size) { index ->
                ContactItem(contact = contacts[index])
            }

        }

    }
}