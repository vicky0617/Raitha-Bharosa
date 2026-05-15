package com.example.raithabharosahub.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse

    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): ForecastResponse
}

data class WeatherResponse(
    val main: MainData,
    val weather: List<WeatherDescription>,
    val wind: WindData?,
    val rain: RainData?,
    val name: String
)

data class MainData(
    val temp: Double,
    val humidity: Int
)

data class WindData(
    val speed: Double
)

data class RainData(
    @com.squareup.moshi.Json(name = "1h") val oneHour: Double?
)

data class WeatherDescription(
    val main: String,
    val description: String,
    val icon: String
)

data class ForecastResponse(
    val list: List<ForecastItem>
)

data class ForecastItem(
    val dt: Long,
    val main: MainData,
    val weather: List<WeatherDescription>,
    val wind: WindData?,
    val rain: RainData?
)
