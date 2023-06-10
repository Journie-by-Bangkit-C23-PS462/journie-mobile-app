package com.dicoding.journie.data.network.api

import com.dicoding.journie.data.network.response.Destination
import com.dicoding.journie.data.network.response.ExploreDestination
import retrofit2.http.GET

interface APIService {
    @GET("explore_jakarta")
    suspend fun getJakartaDestination() : ExploreDestination

    @GET("explore_bandung")
    suspend fun getBandungDestination() : ExploreDestination

    @GET("explore_surabaya")
    suspend fun getSurabayaDestination() : ExploreDestination

    @GET("explore_jogja")
    suspend fun getJogjaDestination() : ExploreDestination

    @GET("explore_semarang")
    suspend fun getSemarangDestination() : ExploreDestination
}