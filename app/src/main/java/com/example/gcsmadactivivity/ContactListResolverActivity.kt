package com.example.gcsmadactivivity

import android.content.ContentResolver
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gcsmadactivivity.models.Contact
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

class ContactListResolverActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ResolverUIScreen(
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestContactPermission(onPermissionGranted: () -> Unit) {
    val context = LocalContext.current
    val permissionState = rememberPermissionState(android.Manifest.permission.READ_CONTACTS)

    LaunchedEffect(permissionState.status) {
        if (permissionState.status.isGranted) {
            onPermissionGranted()
        } else {
            permissionState.launchPermissionRequest()
        }
    }
}

@Composable
fun ResolverUIScreen(name: String, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    var contacts by remember { mutableStateOf(listOf<Contact>()) }

    RequestContactPermission {

        val contentResolver: ContentResolver = context.contentResolver
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phone =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                val uri =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI))

                contacts += Contact(id = id.toInt(), name = name, pictureUri = uri)


            }
            cursor.close()
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LazyColumn {
            items(contacts) {
                ContactCard(contact = it)
                VerticalDivider()
            }
        }


    }
}

@Composable
fun ContactCard(modifier: Modifier = Modifier, contact: Contact) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = modifier.padding(8.dp)) {
            Text(text = "id : ${contact.id}")
            Text(text = "Name : ${contact.name}")
            Text(text = "Phone : ${contact.phone}")
            Text(text = "URI : ${contact.pictureUri}")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ResolverUIScreenPreview() {
    GCSMADActivivityTheme {
        ResolverUIScreen("Android")
    }
}