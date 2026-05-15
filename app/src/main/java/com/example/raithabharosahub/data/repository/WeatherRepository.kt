package com.example.raithabharosahub.data.repository

import com.example.raithabharosahub.data.remote.WeatherApi
import com.example.raithabharosahub.data.remote.WeatherResponse
import com.example.raithabharosahub.data.remote.ForecastResponse

class WeatherRepository(private val weatherApi: WeatherApi) {
    private val apiKey = "YOUR_API_KEY_HERE" // Ideally this should be in a safer place

    suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherResponse> {
        return try {
            val response = weatherApi.getCurrentWeather(lat, lon, apiKey)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getForecast(lat: Double, lon: Double): Result<ForecastResponse> {
        return try {
            val response = weatherApi.getForecast(lat, lon, apiKey)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
