package com.example.farm_able

import com.example.farm_able.PlantDiseaseModel.PlantDisease
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface PlantDiseaseService {
    @Multipart
    @POST("identify")
    suspend fun identifyDisease(
        @Part images: List<MultipartBody.Part>,
        @Query("include-related-images") includeRelatedImages: Boolean = false,
        @Query("no-reject") noReject: Boolean = false,
        @Query("nb-results") nbResults: Int = 10,
        @Query("lang") lang: String = "en",
        @Query("api-key") apiKey: String = "2b10wMqoxaPArVKMpnhwy4m7Ie"
    ): PlantDisease
}
