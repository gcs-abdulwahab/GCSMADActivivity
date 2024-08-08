package com.example.gcsmadactivivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import kotlinx.coroutines.launch

class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {
                ScaffoldUI()
            }
        }
    }

}

@Composable
fun ScaffoldUI() {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }

    ) { innerpadding ->

        Box(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            Column {

                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Button")
                }
                Button(onClick = {

                    scope.launch {
                        var result = snackbarHostState.showSnackbar(
                            "Hello", actionLabel = "Action",

                            duration = SnackbarDuration.Indefinite,
                            withDismissAction = true
                        )

                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                Log.d("TAGA", "ScaffoldUI: ActionPerformed")
                            }

                            SnackbarResult.Dismissed -> {
                                Log.d("TAGA", "ScaffoldUI: Dismissed")
                            }
                        }
                    }


                }) {
                    Text("Show Snackbar")
                }

            }
        }

    }
}


@Composable
fun Screen2() {
    Column {
        Text("Screen2")
    }
}
