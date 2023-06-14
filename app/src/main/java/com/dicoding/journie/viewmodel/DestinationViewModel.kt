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

    private val _bandungPlaceList = MutableStateFlow<List<Destination>>(emptyList())
    val bandungPlacesList : StateFlow<List<Destination>> = _bandungPlaceList

    private val _semarangPlaceList = MutableStateFlow<List<Destination>>(emptyList())
    val semarangPlacesList : StateFlow<List<Destination>> = _semarangPlaceList

    private val _surabayaPlaceList = MutableStateFlow<List<Destination>>(emptyList())
    val surabayaPlacesList : StateFlow<List<Destination>> = _surabayaPlaceList

    private val _jogjaPlaceList = MutableStateFlow<List<Destination>>(emptyList())
    val jogjaPlacesList : StateFlow<List<Destination>> = _jogjaPlaceList


    init {
        viewModelScope.launch {
            repository.startRefreshAPICall(5000, this@launch) {
                _jakartaPlaceList.value = repository.getJakartaPlaces()
                _bandungPlaceList.value = repository.getBandungPlaces()
                _surabayaPlaceList.value = repository.getSurabayaPlaces()
                _semarangPlaceList.value = repository.getSemarangPlaces()
                _jogjaPlaceList.value = repository.getJogjaPlaces()
            }
        }
    }
}