package com.dicoding.journie.data.network.api

import com.dicoding.journie.data.network.response.CreatePlanResponse
import com.dicoding.journie.data.network.response.Destination
import com.dicoding.journie.data.network.response.ExploreDestination
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

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

    @Headers("Content-Type: application/json")
    @POST("planmodel")
    fun createPlanmodel(@Body body: JsonObject) : Call<CreatePlanResponse>
}