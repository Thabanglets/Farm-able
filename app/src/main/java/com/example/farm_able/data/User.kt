package com.example.farm_able.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "Users")
data class User (
    @PrimaryKey(autoGenerate = true)
    val UserID: Int = 0,
    val FirstName: String,
    val LastName: String,
    val Email: String,
    val PasswordHash: String,
    val Language: String,
    val Location: String,
    val FarmName: String
)
