package com.example.gcsmadactivivity.models

data class Weather(
    val city: String = "San Francisco",
    val description: String = "Mostly Cloudy",
    val weatherImage: String = "https://freesvg.org/img/weather-overcast.png",
    val temp: Int = 32,
    val rain: Int = 2,
    val wind: Int = 10,
    val uv: String = "Moderate"
)
