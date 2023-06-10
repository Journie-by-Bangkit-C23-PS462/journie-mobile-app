package com.dicoding.journie.data

import com.dicoding.journie.data.network.api.APIService
import com.dicoding.journie.data.network.response.Destination


class Repository private constructor(
    private val apiService: APIService
){

    suspend fun getJakartaPlaces() : List<Destination> {
        val response = apiService.getJakartaDestination()
        return response.data.toList()
    }

    suspend fun getBandungPlaces() : List<Destination> {
        val response = apiService.getBandungDestination()
        return response.data.toList()
    }

    suspend fun getSurabayaPlaces() : List<Destination> {
        val response = apiService.getSurabayaDestination()
        return response.data.toList()
    }

    suspend fun getSemarangPlaces() : List<Destination> {
        val response = apiService.getSemarangDestination()
        return response.data.toList()
    }

    suspend fun getJogjaPlaces() : List<Destination> {
        val response = apiService.getJogjaDestination()
        return response.data.toList()
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            apiService: APIService
        ) : Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(apiService)
            }.also { instance = it }
    }
}