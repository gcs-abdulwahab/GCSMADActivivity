package com.example.gcsmadactivivity

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {

                Card1(Contact("Name 1", 1))
            }
        }
    }
}

data class Contact(val name: String, val id: Int) {
    companion object {
        val contacts = (1..100).map { Contact("Name $it", it) }
    }
}


@Composable
fun Card1(contact: Contact, modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    ElevatedCard() {
        Row(
            verticalAlignment = CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 8.dp),
        ) {

            Image(
                painter = painterResource(id = R.drawable.ansar),
                contentDescription = "Contact Image",
                modifier = Modifier.size(80.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = contact.id.toString())
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            ElevatedButton(onClick = {
                Log.d("SecondActivity", "Button clicked $expanded")
                expanded = !expanded
            }) {
                Text(text = "expanded ${expanded}")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CarPreview() {
    LazyColumn {
        items(Contact.contacts) { contact ->
            Card1(contact = contact)
            VerticalDivider()
            Spacer(modifier = Modifier.height(4.dp))
        }
    }


}