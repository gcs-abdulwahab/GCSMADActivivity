package com.example.gcsmadactivivity

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import kotlin.math.ceil
import kotlin.math.round

class TipActivity : ComponentActivity() {
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
fun TipCalculator() {

    var amount by remember { mutableIntStateOf(25) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = amount.toString(),
            onValueChange = {
                amount = it.toIntOrNull() ?: 0
            },
            label = { Text("Bill Amount") },
            modifier = Modifier.padding(16.dp)
        )
       Text(text = "Tip Amount: ${ ceil(amount * 0.1).toInt()  }")



//         Round the amount in integer
        Text(text = "Total Amount: ${ round(amount * 1.1).toInt() }")


    }


}

@Preview(showBackground = true, widthDp = 360, heightDp = 300)
@Composable
fun TipCalculatorPreview() {
    GCSMADActivivityTheme {
        TipCalculator()
    }
}