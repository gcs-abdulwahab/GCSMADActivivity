package com.example.gcsmadactivivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gcsmadactivivity.models.Contact
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import kotlin.random.Random

val TAG = "FigmaActivity"

class FigmaListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {

<<<<<<< HEAD
                ItemList()
=======

>>>>>>> 94a07268227e5a4a75780b490d741b6a10086d01
            }
        }
    }
}

@Composable
fun Item(contact: Contact) {
    var isChecked by remember { mutableStateOf(false) }
    val mycontact by remember { mutableStateOf(contact) }

    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp)


    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier
                .padding(16.dp)
                .size(100.dp)
                .clip(CircleShape)

        )
        Text(
            text = contact.name,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall,

            )
        Text(
            text = contact.id.toString(),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            checked = isChecked, onCheckedChange = {
                isChecked = !isChecked
            }, modifier = Modifier.padding(16.dp)

        )
        val context = LocalContext.current
        Button(onClick = {
            Intent(context, DetailItemActivity::class.java).also {
                context.startActivity(it)

                Log.d("Detail Item", "Detail Item Clicked")

            }

        }) {
            Text(text = "Go")
        }


    }


}

@Composable
fun ItemList() {
    LazyColumn {
        items(Contact.contacts) { contact ->
            Item(contact = contact)
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ItemListPreview() {

    ItemList()

}

@Preview(heightDp = 100)
@Composable
fun DefaultPreview() {
    Item(Contact(id = 1,name = "Default"))
}