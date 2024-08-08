package com.example.gcsmadactivivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gcsmadactivivity.ui.theme.WeatherAppTheme

class AIWeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherDisplay()
                }
            }
        }
    }
}

@Composable
fun WeatherDisplay() {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "London", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile), // Replace with actual resource
                contentDescription = "Weather Icon",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "74°F", fontSize = 48.sp)
                Text(text = "L70° H74°", fontSize = 16.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        HourlyForecast()
    }
}

@Composable
fun HourlyForecast() {
    val hourlyData = listOf(
        "1400" to "74°F",
        "1500" to "74°F",
        "1600" to "74°F",
        "1700" to "73°F",
        "1800" to "72°F",
        "1900" to "71°F",
        "2000" to "70°F"
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        hourlyData.forEach { (time, temp) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = time, fontSize = 12.sp)
                Image(
                    painter = painterResource(id = R.drawable.profile), // Replace with actual resource
                    contentDescription = "Hourly Weather Icon",
                    modifier = Modifier.size(24.dp)
                )
                Text(text = temp, fontSize = 12.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherDisplayPreview() {
    WeatherAppTheme {
        WeatherDisplay()
    }
}