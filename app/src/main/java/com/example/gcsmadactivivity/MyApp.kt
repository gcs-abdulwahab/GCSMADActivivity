package com.example.gcsmadactivivity

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

class MyApp : Application() {

    val dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

}