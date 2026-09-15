package com.example.farm_able

import com.example.farm_able.weatherModel.Metao
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
        @GET("forecast")
        suspend fun getWeatherForecast(
            @Query("q") city: String,
            @Query("appid") apiKey: String,
            @Query("units") units: String = "metric"
        ): Metao
}