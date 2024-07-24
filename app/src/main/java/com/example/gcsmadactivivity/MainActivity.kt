package com.example.gcsmadactivivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//   list /       collection of 5 answers
        val answers = listOf(
            Answer(sum(5, 4), "Answer   ${sum(5, 4)}     "),
            Answer(2, "Answer 2"),
            Answer(3, "Answer 3"),
            Answer(4, "Answer 4"),
            Answer(5, "Answer 5"),
            Answer(3, "Answer 3"),
            Answer(4, "Answer 4"),
            Answer(5, "Answer 5"),
            Answer(3, "Answer 3"),
            Answer(4, "Answer 4"),
            Answer(5, "Answer 5")
        )


        setContent {
            GCSMADActivivityTheme {
//  scaffold in such a way that  greetings are vertically stacked

                Scaffold(
                    contentColor = Color.Red,

                    content = {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {

//                            for each answers   create a SurveyAnswer
                            answers.forEach { answer ->
                                SurveyAnswer(answer)
                            }

//                            SurveyAnswer(Answer(sum(5, 4), "Answer   ${sum(5, 4)}     "))
//                            SurveyAnswer(Answer(2, "Answer 2"))
//                            SurveyAnswer(Answer(3, "Answer 3"))
//                            SurveyAnswer(Answer(4, "Answer 4"))

                        }
                    }
                )


            }
        }
    }
}


data class Answer(val id: Int, val answer: String)


fun sum(a: Int, b: Int): Int {
    return a + b
}


@Composable
fun SurveyAnswer(answer: Answer) {
    Row(
        modifier = Modifier.padding(16.dp)
        //        add a little space between each row element

    ) {
        Text(text = answer.id.toString())
        Text(text = answer.answer)
    }
}


@Composable
fun Greeting(fname: String = "User", modifier: Modifier = Modifier) {

    Row(
        modifier = modifier.padding(16.dp)
    ) {
        Text(text = "Hello, $fname!")
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GCSMADActivivityTheme {
        SurveyAnswer(Answer(1, "Answer 1"))
    }
}