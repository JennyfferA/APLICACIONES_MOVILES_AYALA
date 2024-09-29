package com.example.coches.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Coche::class], version = 1, exportSchema = false)
abstract class CocheDatabase : RoomDatabase() {
    abstract fun cocheDao(): CocheDao

    companion object {
        @Volatile
        private var INSTANCE: CocheDatabase? = null

        fun getDatabase(context: Context): CocheDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CocheDatabase::class.java,
                    "coche_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}