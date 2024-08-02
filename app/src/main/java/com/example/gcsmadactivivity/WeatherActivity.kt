package com.example.gcsmadactivivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gcsmadactivivity.models.Weather
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme

class WeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {

                WeatherScreen()
            }
        }
    }
}


@Composable
fun WeatherScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        val weatherData = Weather()

        WeatherHeader()
        WeatherCity()
        WeatherStats (weatherData)

    }

}

@Composable
fun WeatherStats(weatherData: Weather) {
    Column(
        Modifier
            .background(color = androidx.compose.ui.graphics.Color.Yellow)
            .padding(vertical = 2.dp), verticalArrangement = Arrangement.Center


    ) {

//        // make a collection with key and value pairs
//
//        val stats =
//            listOf( ("Temperature" to weatherData.temp),
//                ("Rain" to weatherData.rain),
//                ("Wind" to weatherData.wind),
//                ("UV" to weatherData.uv)
//            )


        Weather().stats.forEach { (key, value) ->
            Row(
                Modifier
                    .background(color = Color.Cyan)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = key,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(8.dp),

                    )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "$value",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(8.dp)

                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 400)
@Composable
fun WeatherStatsPreview() {
    GCSMADActivivityTheme {

        WeatherStats(Weather())
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun WeatherHeaderPreview() {
    GCSMADActivivityTheme {
        WeatherHeader()

    }
}


@Composable
fun WeatherCity() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Blue)
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.Green)
                .padding(8.dp)

        ) {
            Text(text = "City", fontSize = 50.sp, style = MaterialTheme.typography.labelMedium)
            Text(text = "Country", fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ansar),
            contentDescription = "Logo",
            Modifier.size(100.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun WeatherCityPreview() {
    GCSMADActivivityTheme {
        WeatherCity()
    }
}


@Composable
fun WeatherHeader() {
    Row(
        modifier = Modifier
            .background(color = androidx.compose.ui.graphics.Color.Red)
            .fillMaxWidth()
            .padding(8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ansar),
            contentDescription = "Logo",
            Modifier.size(100.dp)
        )
        Text(text = "Weather", fontSize = 30.sp)
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ansar),
            contentDescription = "Logo",
            Modifier.size(75.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ansar),
            contentDescription = "Logo",
            Modifier.size(75.dp)
        )


    }

}

@Composable
@Preview(showBackground = true)
fun WeatherScreenPreview() {
    GCSMADActivivityTheme {
        WeatherScreen()
    }
}