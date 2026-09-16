package com.example.farm_able

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object plantDiseaseClient {
    private const val BASE_URL = "https://my-api.plantnet.org/v2/diseases/"

    val instance: PlantDiseaseService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlantDiseaseService::class.java)
    }
}
