package com.dicoding.journie.viewmodel

import com.dicoding.journie.data.Repository
import com.dicoding.journie.data.network.api.APIConfig

object Injection {
    fun provideRepository() : Repository {
        val apiService = APIConfig.getServiceWithoutAuth()
        return Repository.getInstance(apiService)
    }
}