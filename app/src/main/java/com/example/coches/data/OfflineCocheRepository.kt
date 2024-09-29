package com.example.coches.data

import kotlinx.coroutines.flow.Flow

class OfflineCocheRepository(private val cocheDao: CocheDao) : CocheRepository {

    override fun getAllCochesStream(): Flow<List<Coche>> = cocheDao.getAllCoches()

    override fun getCocheStream(id: Int): Flow<Coche?> = cocheDao.getCoche(id)

    override suspend fun insertCoche(coche: Coche) = cocheDao.insert(coche)

    override suspend fun deleteCoche(coche: Coche) = cocheDao.delete(coche)

    override suspend fun updateCoche(coche: Coche) = cocheDao.update(coche)
}