package com.example.farm_able.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class FarmableDb : RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCES : FarmableDb? = null

        fun getDatabase (context: Context): FarmableDb{
            val tempInstance = INSTANCES
            if (tempInstance != null){
                return  tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FarmableDb::class.java,
                    "farmable_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCES = instance
                return instance
            }
        }
    }
}
