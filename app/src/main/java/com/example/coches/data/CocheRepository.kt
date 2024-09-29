package com.example.coches.data

import kotlinx.coroutines.flow.Flow

interface CocheRepository {

        fun getAllCochesStream(): Flow<List<Coche>>

        fun getCocheStream(id: Int): Flow<Coche?>

        suspend fun insertCoche(item: Coche)

        suspend fun deleteCoche(item: Coche)

        suspend fun updateCoche(item: Coche)
}
