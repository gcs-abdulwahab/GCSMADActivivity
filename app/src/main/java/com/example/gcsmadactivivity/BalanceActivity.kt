package com.example.gcsmadactivivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import models.Contact

class BalanceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {
                BalanceCard(
                    contact = Contact(
                        1,
                        "Default",
                        imageUrl = "https://picsum.photos/200/300",
                        balance = 800
                    )
                )

            }
        }
    }
}

@Composable
fun BalanceCard(contact: Contact, modifier: Modifier = Modifier) {

    var balanceAmount by remember { mutableStateOf(contact.balance) }

    Column {
        Text(
            text = "Name: ${contact.name}", modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Balance: ${balanceAmount}", modifier = Modifier.padding(16.dp)
        )
        Button(onClick = {
            balanceAmount += 1
        }, modifier = Modifier.padding(16.dp, bottom = 0.dp)) {
            Text(text = "Increment")
        }
        Button(onClick = {
            balanceAmount -= 1
        }, modifier = Modifier.padding(16.dp)) {
            Text(text = "Decrement")
        }


    }
}


@Preview(showBackground = true, name = "Light Mode")
@Composable
fun BalanceCardPreview() {
    GCSMADActivivityTheme {
        BalanceCard(
            contact = Contact(
                1, "Default", imageUrl = "https://picsum.photos/200/300", balance = 400
            )
        )
    }
}