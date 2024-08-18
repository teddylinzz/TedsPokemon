package com.example.data.repo

import com.example.data.datasource.remote.ImageDataSource
import com.example.data.module.ImageResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ImageRepositoryImpl(
    private val dataSource: ImageDataSource,
    private val dispatchers: CoroutineDispatcher = Dispatchers.Default
) :
    ImageRepository {
    override suspend fun getImages(limit: Int, offset: Int): Flow<List<ImageResource>> {
        return dataSource.getPokemonName(limit, offset)
            .map { data ->
                data.results.map { item ->
                    ImageResource(
                        name = item.name,
                        url = "https://img.pokemondb.net/artwork/${item.name}.jpg"
                    )
                }
            }
            .flowOn(dispatchers)
    }
}