package com.example.coches.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coches.data.Coche
import com.example.coches.data.CocheRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class CocheViewModel(private val repository: CocheRepository) : ViewModel() {

    val allCoches: Flow<List<Coche>> = repository.getAllCochesStream()

    fun insertCoche(marca: String, modelo: String, ano: Int, kilometraje: Int) {
        val newCoche = Coche(marca = marca, modelo = modelo, ano = ano, kilometraje = kilometraje)
        viewModelScope.launch {
            repository.insertCoche(newCoche)
        }
    }

    fun deleteCoche(coche: Coche) {
        viewModelScope.launch {
            repository.deleteCoche(coche)
        }
    }

    fun updateCoche(coche: Coche) {
        viewModelScope.launch {
            repository.updateCoche(coche)
        }
    }

    fun getCocheById(id: Int): Flow<Coche?> {
        return repository.getCocheStream(id)
    }
}
