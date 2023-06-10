package com.dicoding.journie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.journie.data.Repository
import com.dicoding.journie.data.network.response.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DestinationViewModel(private val repository: Repository) : ViewModel() {
    private val _jakartaPlaceList = MutableStateFlow<List<Destination>>(emptyList())
    val jakartaPlacesList : StateFlow<List<Destination>> = _jakartaPlaceList

    init {
        viewModelScope.launch {
            _jakartaPlaceList.value = repository.getJakartaPlaces()
        }
    }
}