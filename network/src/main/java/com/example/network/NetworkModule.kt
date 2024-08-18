package com.example.network

import com.example.network.APIService.Companion.API_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val okHttp = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .connectTimeout(60 * 1000L, TimeUnit.MILLISECONDS)
    .build()
private val retrofit = Retrofit.Builder()
    .baseUrl(API_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(okHttp)
    .build()
val apiService = retrofit.create(APIService::class.java)