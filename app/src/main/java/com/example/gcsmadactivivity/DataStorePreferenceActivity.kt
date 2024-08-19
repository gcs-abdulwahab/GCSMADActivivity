package com.example.gcsmadactivivity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.Preferences.Key
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class DataStorePreferenceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCSMADActivivityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),

                    color = MaterialTheme.colorScheme.background
                ) {
                  DataStoreExample()

                }
            }
        }
    }

}

@Composable
fun DataStoreExample() {
    val context = LocalContext.current
    val dataStore = (context.applicationContext as MyApp).dataStore

    // State to hold the text input value
    var textFieldValue by remember { mutableStateOf("") }

    // Launch a coroutine scope for DataStore operations
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),

        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        Text(
            text = "DataStore Preference",
        )

        TextField(value = textFieldValue , onValueChange = {
            textFieldValue = it
        })

        Button(onClick = {
            scope.launch {
                dataStore.edit { preferences ->
                    preferences[stringPreferencesKey("dataStoreKey")] = textFieldValue
                }
            }

        }) {
            Text("Save")
        }

        Button(onClick = {

            scope.launch {
                val data = dataStore.data.first()
                val value = data[stringPreferencesKey("dataStoreKey")]
                Toast.makeText(context, value.toString(), Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Read")
        }


    }
}