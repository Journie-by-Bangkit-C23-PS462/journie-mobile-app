package com.dicoding.journie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.journie.data.Repository
import com.dicoding.journie.data.network.response.CreatePlanResponse
import com.dicoding.journie.data.network.response.Destination
import com.dicoding.journie.data.network.response.DestinationRecommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
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

    val planModelList : StateFlow<List<List<DestinationRecommendation>>> = repository.listPlaces

    val planModelStatus : StateFlow<Boolean> = repository.status

    init {
        viewModelScope.launch {
            repository.startRefreshAPICall(10000, this@launch) {
                _jakartaPlaceList.value = repository.getJakartaPlaces()
                _bandungPlaceList.value = repository.getBandungPlaces()
                _surabayaPlaceList.value = repository.getSurabayaPlaces()
                _semarangPlaceList.value = repository.getSemarangPlaces()
                _jogjaPlaceList.value = repository.getJogjaPlaces()
            }
        }
    }

    fun createPlanModel(
        city: String,
        duration: Int,
        age: Int,
        username: String,
        bahari: Boolean,
        budaya: Boolean,
        tamanHiburan: Boolean,
        cagarAlam: Boolean,
        pusatPerbelanjaan: Boolean,
        tempatIbadah: Boolean
    ) {
        viewModelScope.launch {
            repository.createPlanModel(city, duration, age, username, bahari, budaya, tamanHiburan, cagarAlam, pusatPerbelanjaan, tempatIbadah)
        }
    }

//    fun savePlanModel(
//        data:
//    )
}