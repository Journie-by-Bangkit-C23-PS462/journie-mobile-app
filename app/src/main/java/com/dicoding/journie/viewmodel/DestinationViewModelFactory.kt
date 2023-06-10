package com.dicoding.journie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.journie.data.Repository

class DestinationViewModelFactory private constructor(private val repository: Repository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DestinationViewModel::class.java)) {
            return DestinationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknwon ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: DestinationViewModelFactory? = null

        fun getInstance() : DestinationViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: DestinationViewModelFactory(Injection.provideRepository())
            }.also { instance = it }
    }
}