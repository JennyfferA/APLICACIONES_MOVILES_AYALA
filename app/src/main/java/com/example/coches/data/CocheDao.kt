package com.example.coches.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the Inventory database
 */
@Dao
interface CocheDao {

    @Query("SELECT * from coches ORDER BY marca ASC")
    fun getAllCoches(): Flow<List<Coche>>

    @Query("SELECT * from coches WHERE id = :id")
    fun getCoche(id: Int): Flow<Coche>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(coche: Coche)

    @Update
    suspend fun update(coche: Coche)

    @Delete
    suspend fun delete(coche: Coche)
}