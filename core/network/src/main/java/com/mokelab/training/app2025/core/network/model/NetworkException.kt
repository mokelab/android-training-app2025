package com.mokelab.training.app2025.core.network.model

class NetworkException(
    val status: Int,
    val body: String,
) : Exception()