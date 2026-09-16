package com.example.farm_able.PlantDiseaseModel

data class PlantDisease(
    val language: String,
    val query: Query,
    val remainingIdentificationRequests: Int,
    val results: List<Result>,
    val version: String
)