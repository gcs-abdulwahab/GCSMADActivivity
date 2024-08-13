package com.example.gcsmadactivivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import models.Course

class ThirdActivity : ComponentActivity() {
    private val TAG: String? = "ThirdActivityLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {

                val color = intent.getStringExtra("color")
                Log.d(TAG, "onCreate: $color")
                // A surface container using the 'background' color from the theme

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(color = Color.Yellow)
                ) {
                    val name = intent.getStringExtra("name")


//                    Text(text ="Hello Third Activity!")
                    Spacer(modifier = Modifier.height(16.dp))

                    Greeting3(name = name.toString())

                    AsyncImage(
                        model = "https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg",
                        contentDescription = "image"
                    )

                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
    ) {
        Text(
            text = name, modifier = modifier
        )
    }
}

@Composable
fun Welcome(course: Course) {


    Column(modifier = Modifier.background(color = Color.Red)) {
        Text(text = "Hello ${course.name}!")
        Text(text = "Credit Hours: ${course.credithrs}")
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview3() {
//        val c1 = Course(1,"Kotlin")
//    create a list of 10 courses
    val courses = listOf(
        Course(1, "as2"), Course(2, "rew"), Course(2, "Tew", 4)
    )



    GCSMADActivivityTheme {
        Surface {
            Column {
                for (course in courses) {
                    Welcome(course = course)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

    }

}