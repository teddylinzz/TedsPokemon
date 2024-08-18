package com.example.tedspokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.data.datasource.remote.ImageRemoteDataSourceImpl
import com.example.data.module.ImageResource
import com.example.data.repo.ImageRepository
import com.example.data.repo.ImageRepositoryImpl
import com.example.network.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean

class MainViewModel(val repo: ImageRepository) : ViewModel() {
    private val _images = MutableLiveData<List<ImageResource>>()
    val images: LiveData<List<ImageResource>> = _images

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var loading = AtomicBoolean(false)
    private var currentOffset = 0

    init {
        getImages(currentOffset)
    }

    private fun getImages(offset: Int) {
        viewModelScope.launch {
            loading.set(true)
            repo.getImages(NUMBER_PER_PAGE, offset)
                .catch { _error.postValue(it.message) }
                .flowOn(Dispatchers.Default)
                .onCompletion { loading.set(false) }
                .collect {
                    _images.postValue(it)
                    currentOffset += NUMBER_PER_PAGE
                }
        }
    }

    fun loadMore() {
        if (loading.get()) return
        getImages(currentOffset)
    }

    companion object {
        const val NUMBER_PER_PAGE = 20

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                return MainViewModel(
                    ImageRepositoryImpl(
                        ImageRemoteDataSourceImpl(apiService)
                    )
                ) as T
            }
        }
    }
}