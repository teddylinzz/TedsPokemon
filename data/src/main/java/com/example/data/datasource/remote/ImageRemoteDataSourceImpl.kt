package com.example.data.datasource.remote

import com.example.network.APIService
import com.example.network.apiService
import com.example.network.model.ResponseData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ImageRemoteDataSourceImpl(
    private val service: APIService = apiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    ImageDataSource {
    override suspend fun getPokemonName(limit: Int, offset: Int): Flow<ResponseData> {
        return flow {
            emit(service.getPokemonName(limit, offset))
        }.flowOn(dispatcher)
    }
}