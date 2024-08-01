package com.example.gcsmadactivivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme

class DiscoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {

                ChangeColor()

            }
        }
    }
}


private fun getRandomColor(): Color {
    val listOfColors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow)
    return listOfColors.random()
}

@Composable
fun ChangeColor() {
    val context = LocalContext.current
    var randomcolor = getRandomColor()
    var color by remember { mutableStateOf(Color.Red) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)
    ) {
        Button(onClick = {
            randomcolor = getRandomColor()
            color = randomcolor
        }) {
            Text(text = "Change Color")
        }
        Button(onClick = {
            val intent = Intent(context, ThirdActivity::class.java)
            // send color to the next activity
            intent.putExtra("color", randomcolor.value.toString())
            intent.putExtra("name", "Assignment ")
            context.startActivity(intent)
        }) {
            Text(text = "Send")
        }

        AsyncImage(
            model = "https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg",
            contentDescription = "Image",
        )

    }
}

@Preview()
@Composable
fun DefaultPreview2() {
    GCSMADActivivityTheme {
        ChangeColor()
    }
}