package com.example.gcsmadactivivity

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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gcsmadactivivity.ui.theme.GCSMADActivivityTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

//fun isAirplaneModeOn(context: Context): Boolean {
//    return Settings.Global.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
//}

class AirPlaneViewModel (initial: Boolean = false) : ViewModel() {

    var isAirplaneModeEnabled = MutableStateFlow(initial)

    fun setAirplaneModeStatus(status: Boolean) {
        isAirplaneModeEnabled.value = status
    }


}

@Composable
fun AirPlaneModeScreen(modifier: Modifier = Modifier) {
    // Create ViewModel in composable context\


    val viewModel: AirPlaneViewModel  = viewModel()

    // Observe airplane mode status
    val airstatus by viewModel.isAirplaneModeEnabled.collectAsState()

    // Get the coroutine scope
    val coroutineScope = rememberCoroutineScope()

    // Register the broadcast receiver within the composable context
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val airplaneModeBroadcastReceiver = AirplaneModeBroadcastReceiver { isAirplaneModeEnabled ->
            coroutineScope.launch {
                viewModel.setAirplaneModeStatus(isAirplaneModeEnabled)
            }
        }

        // Register the receiver
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        context.registerReceiver(airplaneModeBroadcastReceiver, filter)

        // Unregister when the composable is removed
        onDispose {
            context.unregisterReceiver(airplaneModeBroadcastReceiver)
        }



    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    viewModel.setAirplaneModeStatus(!airstatus)
                }
            }) {
                Text(text = "Toggle Airplane Mode")
            }

            AirPlaneModeTextStatus(status = airstatus)
        }
    }


}


class BroadActivity : ComponentActivity() {

    private lateinit var airplaneModeBroadcastReceiver: AirplaneModeBroadcastReceiver



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()




        setContent {
            GCSMADActivivityTheme {
                Scaffold() { innerPadding ->

                    AirPlaneModeScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }



@Composable
fun AirPlaneModeTextStatus(
    modifier: Modifier = Modifier,
    status: Boolean = false


) {


    Box() {
        Text(text = "Airplane Mode: $status", modifier = modifier)
    }

}


class AirplaneModeBroadcastReceiver(  private val onAirplaneModeChanged: (Boolean) -> Unit ) : BroadcastReceiver() {

     var viewModel: AirPlaneViewModel = AirPlaneViewModel()


    override fun onReceive(context: Context?, intent: Intent?) {

        // check if  if broadcast is related to airplane mode
        if (intent?.action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            val airplaneMode = intent?.getBooleanExtra("state", false)!!

            onAirplaneModeChanged(airplaneMode)
            Log.d(
                "AirplaneModeBroadcastReceiver",
                "Airplane Mode 32  ${viewModel.isAirplaneModeEnabled.value} "
            )
        }


    }
}