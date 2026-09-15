package com.example.farm_able

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.lazy

//import com.example.farm_able.weatherModel.Weather

object RetrofitClient {
    val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    val instance: WeatherApiService by lazy{

        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(
            WeatherApiService::class.java)

    }
} 