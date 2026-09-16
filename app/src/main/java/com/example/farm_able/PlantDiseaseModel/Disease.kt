package com.example.farm_able.PlantDiseaseModel

data class Disease(
    val name: String, // EPPO code
    val label: String,
    val categories: List<String>
)
