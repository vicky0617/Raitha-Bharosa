package com.example.raithabharosahub.data.repository

import com.example.raithabharosahub.data.remote.WeatherApi
import com.example.raithabharosahub.data.remote.WeatherResponse
import com.example.raithabharosahub.data.remote.ForecastResponse
import com.example.raithabharosahub.data.remote.MainData
import com.example.raithabharosahub.data.remote.WeatherDescription
import com.example.raithabharosahub.data.remote.WindData
import com.example.raithabharosahub.data.remote.RainData

import com.example.raithabharosahub.data.remote.ForecastItem

@Suppress("UNUSED_PARAMETER")
class WeatherRepository(private val weatherApi: WeatherApi) {

    suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherResponse> {
        // BYPASSING NETWORK: Returning mock data directly as per project documentation instructions
        return Result.success(getMockWeather())
    }

    private fun getMockWeather(): WeatherResponse {
        return WeatherResponse(
            main = MainData(temp = 28.5, humidity = 45),
            weather = listOf(WeatherDescription(main = "Clouds", description = "broken clouds", icon = "04d")),
            wind = WindData(speed = 4.12),
            rain = RainData(oneHour = 0.0),
            name = "Dharwad",
        )
    }

    suspend fun getForecast(lat: Double, lon: Double): Result<ForecastResponse> {
        // BYPASSING NETWORK: Returning mock data directly as per project documentation instructions
        return Result.success(getMockForecast())
    }

    private fun getMockForecast(): ForecastResponse {
        val list = listOf(
            ForecastItem(dt = 1715940000, main = MainData(temp = 29.0, humidity = 42), weather = listOf(WeatherDescription(main = "Clear", description = "clear sky", icon = "01d")), wind = WindData(speed = 3.5), rain = null),
            ForecastItem(dt = 1715950800, main = MainData(temp = 27.5, humidity = 48), weather = listOf(WeatherDescription(main = "Clouds", description = "few clouds", icon = "02d")), wind = WindData(speed = 2.8), rain = null),
            ForecastItem(dt = 1715961600, main = MainData(temp = 25.0, humidity = 55), weather = listOf(WeatherDescription(main = "Rain", description = "light rain", icon = "10d")), wind = WindData(speed = 5.2), rain = RainData(oneHour = 0.5))
        )
        return ForecastResponse(list = list)
    }
}
