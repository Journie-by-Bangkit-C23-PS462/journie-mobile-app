package com.dicoding.journie.data

import android.util.Log
import com.dicoding.journie.data.network.api.APIConfig
import com.dicoding.journie.data.network.api.APIService
import com.dicoding.journie.data.network.response.CreatePlanResponse
import com.dicoding.journie.data.network.response.Destination
import com.dicoding.journie.data.network.response.DestinationRecommendation
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.Socket
import java.net.SocketTimeoutException

class Repository private constructor(
    private val apiService: APIService
){

    private var _listPlaces = MutableStateFlow<List<List<DestinationRecommendation>>>(emptyList())
    val listPlaces : StateFlow<List<List<DestinationRecommendation>>> = _listPlaces

    private var _status = MutableStateFlow(false)
    val status : StateFlow<Boolean> = _status

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

        val client = APIConfig.getServiceCreateRecommendation().createPlanmodel(requestBody)

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
                            Log.e(this::class.java.simpleName, "onResponse: Berhasil masukin data}")
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
//
//class CoroutineRetryPolicy(
//    private val maxAttempts: Int = -1,
//    private val shouldRetry: (Throwable) -> Boolean
//) {
//    suspend fun <T> retry(block: suspend() -> T): suspend () -> T {
//        var currentAttempt = 0
//        var lastException: Throwable? = null
//
//        while (maxAttempts == -1 || currentAttempt < maxAttempts) {
//            try {
//                return block
//            } catch (err: Throwable) {
//                if (!shouldRetry(err)) {
//                    throw err
//                }
//                lastException = err
//            }
//            currentAttempt++
//        }
//        throw lastException ?: IllegalStateException("Retry failed without any exception")
//    }
//
//}