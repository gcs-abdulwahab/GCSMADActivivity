package com.example.gcsmadactivivity

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import kotlinx.coroutines.flow.MutableStateFlow

class AirplaneModeViewModel(application: Application) : AndroidViewModel(application) {

    // get the current state of airplane mode
    // and save it to isAirplaneModeOn

    fun isAirplaneModeOn(context: Context): Boolean {
        return Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON,
            0
        ) != 0
    }

    val isAirplaneModeOn = MutableStateFlow(isAirplaneModeOn(application))



    private val airplaneModeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                val isOn = intent.getBooleanExtra("state", false)
                isAirplaneModeOn.value = isOn
            }
        }
    }

    init {
        val intentFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        application.registerReceiver(airplaneModeReceiver, intentFilter)
    }

    override fun onCleared() {
        super.onCleared()
        getApplication<Application>().unregisterReceiver(airplaneModeReceiver)
    }
}


class BroadActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            GCSMADActivivityTheme {

                BroadScreen()

            }
        }
    }
}

@Composable
fun BroadScreen(modifier: Modifier = Modifier, viewModel: AirplaneModeViewModel = viewModel()) {

    Scaffold(
        containerColor = Color.Yellow
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            val isAirplaneModeOn by viewModel.isAirplaneModeOn.collectAsState()
            Log.d("BroadActivity", "isAirplaneModeOn: $isAirplaneModeOn")
            Text(text = "Airplane Mode: $isAirplaneModeOn")



//            AirPlaneModeText()
        }
    }
}

@Composable
fun AirPlaneModeText() {

    Text(
        text = "Air Plane Mode ",
        modifier = Modifier
            .background(Color.Green)
            .padding(8.dp)
    )
}
