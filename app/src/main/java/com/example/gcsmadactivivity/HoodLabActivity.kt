package com.example.gcsmadactivivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme

class HoodLabActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {
                HoodLabScreen()
            }
        }
    }

    @Composable
    fun HoodLabScreen() {
        var username by remember { mutableStateOf("superuser") }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Heading(name = username)
            MyTextField(uname = username, onTextChange = { username = it })
            Button(onClick = {

                Toast.makeText(this@HoodLabActivity, "Hello $username!", Toast.LENGTH_SHORT).show()

            }) {
                Text("Submit")
            }
        }
    }

    @Composable
    private fun MyTextField(uname: String, onTextChange: (String) -> Unit) {

        TextField(value = uname,
            onValueChange = onTextChange,
            label = { Text("User name") })
    }


    @Composable
    fun Heading(name: String, modifier: Modifier = Modifier) {
        Text(text = "Hello $name!", fontSize = 32.sp)
    }

    @Preview(showBackground = true)
    @Composable
    fun HoodLabScreenPreview() {
        GCSMADActivivityTheme {
            HoodLabScreen()
        }
    }
}

