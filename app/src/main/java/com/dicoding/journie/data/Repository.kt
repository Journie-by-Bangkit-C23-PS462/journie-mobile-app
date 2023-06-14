package com.dicoding.journie.data

import com.dicoding.journie.data.network.api.APIService
import com.dicoding.journie.data.network.response.Destination
import kotlinx.coroutines.*
import java.net.SocketTimeoutException

class Repository private constructor(
    private val apiService: APIService
){

    suspend fun getJakartaPlaces() : List<Destination> {
        return try {
            val response = apiService.getJakartaDestination()
            response.data.toList()
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

//    private suspend fun <T> retryIO(
//        timeOutMillis: Long = 5000,
//        block: suspend () -> List<Destination>
//    ) : T {
//        return withTimeoutOrNull(timeOutMillis) {
//            retryPolicy.retry { block }
//        } ?: throw SocketTimeoutException("Timeout Occurred")
//    }


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