package com.example.coches.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "coches")
data class Coche(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val marca: String,
    val modelo: String,
    val ano: Int,
    val kilometraje: Int
)