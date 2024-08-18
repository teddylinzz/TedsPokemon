package com.example.data.datasource.remote

import com.example.network.model.ResponseData
import kotlinx.coroutines.flow.Flow

interface ImageDataSource {
    suspend fun getPokemonName(limit: Int, offset: Int): Flow<ResponseData>
}