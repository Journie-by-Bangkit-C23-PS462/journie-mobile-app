package com.dicoding.journie.data.network.api

import com.dicoding.journie.data.network.response.ExploreDestination
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("explore_jakarta")
    suspend fun getJakartaDestination() : ExploreDestination

    @GET("explore_bandung")
    fun getBandungDestination() : Call<ExploreDestination>

    @GET("explore_surabaya")
    fun getSurabayaDestination() : Call<ExploreDestination>

    @GET("explore_jogja")
    fun getJogjaDestination() : Call<ExploreDestination>

    @GET("explore_semarang")
    fun getSemarangDestination() : Call<ExploreDestination>
}