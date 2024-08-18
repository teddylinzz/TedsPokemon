package com.example.network.model

data class ResponseData(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Result>
)