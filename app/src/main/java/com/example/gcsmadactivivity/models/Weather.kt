package com.example.gcsmadactivivity.models

data class Weather(
    val city: String = "San Francisco",
    val description: String = "Mostly Cloudy",
    val weatherImage: String = "https://freesvg.org/img/weather-overcast.png",
    val temp: Int = 32,
    val rain: Int = 2,
    val wind: Int = 10,
    val uv: String = "Moderate"


) {


 // expose  temp , rain , wind , uv in a getter
    val stats: List<Pair<String, Any>>
        get() = listOf(
            "Temperature" to temp,
            "Rain" to rain,
            "Wind" to wind,
            "UV" to uv
        )





}
