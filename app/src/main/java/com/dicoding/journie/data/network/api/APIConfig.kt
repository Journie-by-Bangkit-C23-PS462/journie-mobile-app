package com.dicoding.journie.data.network.api

//import com.dicoding.journie.BuildConfig
import com.dicoding.journie.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.sql.Time
import java.util.concurrent.TimeUnit

class APIConfig {
    companion object {
        fun getServiceWithoutAuth() : APIService {
//            val loggingInterceptor = if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//            } else {
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//            }

            val client = OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            val retrofitInstance = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_JOURNIE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofitInstance.create(APIService::class.java)
        }

        fun getServiceCreateRecommendation() : APIService {
            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            val retrofitInstance = Retrofit.Builder()
                .baseUrl("https://mldeployfinal-ancxei2osq-et.a.run.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofitInstance.create(APIService::class.java)

        }
    }
}