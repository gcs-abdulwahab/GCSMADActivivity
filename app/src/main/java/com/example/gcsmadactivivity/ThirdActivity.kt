package com.example.gcsmadactivivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
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
        Course(1, "as2"),
        Course(2, "rew"),
        Course(2, "Tew", 4)
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