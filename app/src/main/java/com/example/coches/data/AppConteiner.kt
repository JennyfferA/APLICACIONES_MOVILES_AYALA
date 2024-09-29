package com.example.coches.data

import android.content.Context

interface AppContainer{
    val cocheRepository: CocheRepository
}


class AppDataContainer(private val context: Context) : AppContainer {

    override val cocheRepository : CocheRepository by lazy {
        OfflineCocheRepository(CocheDatabase.getDatabase(context).cocheDao())
    }
}