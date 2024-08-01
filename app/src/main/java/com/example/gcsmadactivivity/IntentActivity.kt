package com.example.gcsmadactivivity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme

class IntentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {
                ComponentA()
            }
        }
    }

    @Composable
    fun ComponentA(modifier: Modifier = Modifier) {

        val context = LocalContext.current

        Button(onClick = {
            context.startActivity(Intent(context, ReceivingActivity::class.java))
        }) {
            Text(text = "Send to Receiving Activity")

        }
    }


    @Preview
    @Composable
    fun ComponentAPreview() {

        ComponentA()

    }

}