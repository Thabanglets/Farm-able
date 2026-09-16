package com.example.farm_able.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)
    @Query("SELECT * FROM Users ORDER BY UserID ASC")
    fun readAllData() : LiveData<List<User>>

    @Query("SELECT * FROM Users WHERE Email = :email AND PasswordHash = :passwordHash LIMIT 1")
    suspend fun getUserByEmailAndPassword(email: String, passwordHash: String): User?
}