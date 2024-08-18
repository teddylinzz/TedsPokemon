package com.example.data.repo

import com.example.data.module.ImageResource
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun getImages(limit: Int, offset: Int): Flow<List<ImageResource>>
}