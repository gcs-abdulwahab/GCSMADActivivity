package com.example.gcsmadactivivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme

class DemoScaffold2Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {

            }
        }
    }
}

@Composable
fun ScaffoldScreen(modifier: Modifier = Modifier) {
    Scaffold(

        topBar =
        {
            Text(text = "TopAppBar")
        },

        bottomBar = {
            Text(text = "BottomAppBar")
        },
        floatingActionButton = {
            Text(text = "FloatingActionButton")
        }

                modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            Greeting4("Android")
        }

    }
    
}

@Composable
fun Greeting4(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    GCSMADActivivityTheme {
        ScaffoldScreen()
    }
}