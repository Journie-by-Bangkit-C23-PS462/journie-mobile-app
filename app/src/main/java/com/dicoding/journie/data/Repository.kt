package com.dicoding.journie.data

import android.util.Log
import com.dicoding.journie.data.network.api.APIConfig
import com.dicoding.journie.data.network.api.APIService
import com.dicoding.journie.data.network.response.*
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class Repository private constructor(
    private val apiService: APIService
){

    private var _listPlaces = MutableStateFlow<List<List<DestinationRecommendation>>>(emptyList())
    val listPlaces : StateFlow<List<List<DestinationRecommendation>>> = _listPlaces

    private var _status = MutableStateFlow(false)
    val status : StateFlow<Boolean> = _status

    private var _id = MutableStateFlow(0)
    val id : StateFlow<Int> = _id

    private var _savePlanResponse = MutableStateFlow("")
    val savePlanResponse : StateFlow<String> = _savePlanResponse

    private var _activePlanData = MutableStateFlow<List<List<List<DestinationRecommendation>>>?>(emptyList())
    val activePlanData : StateFlow<List<List<List<DestinationRecommendation>>>?> = _activePlanData

    private var _activePlanStatus = MutableStateFlow<Boolean>(false)
    val activePlanStatus : StateFlow<Boolean> = _activePlanStatus

    fun getActivePlan(
        username: String
    ) {
        val requestBody = JsonObject()
        requestBody.addProperty("username", username)

        val client = APIConfig.getRecommendationPlanService().getActivePlan(requestBody)

        try {
            client.enqueue(
                object : Callback<ActivePlanResponse> {
                    override fun onResponse(
                        call: Call<ActivePlanResponse>,
                        response: Response<ActivePlanResponse>
                    ) {
                        var responses = response.body()
                        if (response.isSuccessful) {
                            if (responses != null) {
                                _activePlanData.value = responses.data
                                _activePlanStatus.value = responses.status
                                Log.e(this::class.java.simpleName, "onResponse: Berhasil ambil data active plan")
                            }
                        }
                    }

                    override fun onFailure(call: Call<ActivePlanResponse>, t: Throwable) {
                        Log.e(this::class.java.simpleName, "onFailure: ${t.message}")
                    }
                }
            )
        } catch (e: Exception) {
            Log.e(this::class.java.simpleName, "Exception: ${e.message}")
            _activePlanData.value = emptyList()
            _activePlanStatus.value = false
        }
    }

    fun savePlan(
        planID : Int
    ) {
        val requestBody = JsonObject()
        requestBody.addProperty("plan_id", planID)

        val client = APIConfig.getRecommendationPlanService().savePlan(requestBody)

        client.enqueue(
            object : Callback<SavePlanResponse> {
                override fun onResponse(
                    call: Call<SavePlanResponse>,
                    response: Response<SavePlanResponse>
                ) {
                    var responses = response.body()
                    if (response.isSuccessful) {
                        if (responses != null) {
                            _savePlanResponse.value = responses.data
                            Log.e(this::class.java.simpleName, "onResponse: Berhasil simpan data")
                        }
                    }
                }

                override fun onFailure(call: Call<SavePlanResponse>, t: Throwable) {
                    Log.e(this::class.java.simpleName, "onFailure: ${t.message}")
                }
            }
        )
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

        val requestBody = JsonObject()
        requestBody.addProperty("city", city)
        requestBody.addProperty("duration", duration)
        requestBody.addProperty("age", age)
        requestBody.addProperty("username", username)
        requestBody.addProperty("bahari", bahari)
        requestBody.addProperty("budaya", budaya)
        requestBody.addProperty("tamanHiburan", tamanHiburan)
        requestBody.addProperty("cagarAlam", cagarAlam)
        requestBody.addProperty("pusatPerbelanjaan", pusatPerbelanjaan)
        requestBody.addProperty("tempatIbadah", tempatIbadah)

        val client = APIConfig.getRecommendationPlanService().createPlanmodel(requestBody)

        client.enqueue(
            object : Callback<CreatePlanResponse> {
                override fun onResponse(
                    call: Call<CreatePlanResponse>,
                    response: Response<CreatePlanResponse>
                ) {
                    var responses = response.body()
                    if (response.isSuccessful) {
                        if (responses != null) {
                            _status.value = responses.status
                            _listPlaces.value = responses.data
                            _id.value = responses.planID
                            Log.e(this::class.java.simpleName, "onResponse: Berhasil masukin data")
                        }
                    }
                }

                override fun onFailure(call: Call<CreatePlanResponse>, t: Throwable) {
                    Log.e(this::class.java.simpleName, "onFailure: ${t.message}")
                }
            }
        )
    }

    suspend fun getJakartaPlaces() : List<Destination> {
        return try {
            val response = apiService.getJakartaDestination()
            response.data.toList()
        } catch (e: SocketTimeoutException) {
            emptyList()
        }
    }

    suspend fun getBandungPlaces() : List<Destination> {
        return try {
            val response = apiService.getBandungDestination()
            return response.data.toList()
        } catch (e: SocketTimeoutException) {
            emptyList()
        }
    }

    suspend fun getSurabayaPlaces() : List<Destination> {
        return try {
            val response = apiService.getSurabayaDestination()
            return response.data.toList()
        } catch (e: SocketTimeoutException) {
            emptyList()
        }
    }

    suspend fun getSemarangPlaces() : List<Destination> {
        return try {
            val response = apiService.getSemarangDestination()
            return response.data.toList()
        } catch (e: SocketTimeoutException) {
            emptyList()
        }
    }

    suspend fun getJogjaPlaces() : List<Destination> {
        return try {
            val response = apiService.getJogjaDestination()
            return response.data.toList()
        } catch (e: SocketTimeoutException) {
            emptyList()
        }
    }


    private fun refreshAPICallPeriodically(
        refreshIntervalMillis: Long,
        coroutineScope: CoroutineScope,
        refreshAction: suspend () -> Unit
    ) : Job {
        return coroutineScope.launch {
            while (isActive) {
                refreshAction.invoke()
                delay(refreshIntervalMillis)
            }
        }
    }

    fun startRefreshAPICall(
        refreshIntervalMillis: Long,
        coroutineScope: CoroutineScope,
        refreshAction: suspend () -> Unit
    ) : Job {
        return refreshAPICallPeriodically(refreshIntervalMillis, coroutineScope, refreshAction)
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