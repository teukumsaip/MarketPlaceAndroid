package com.example.marketplace.core.data.source.remote

import com.example.marketplace.core.data.source.remote.network.ApiConfig
import com.example.marketplace.core.data.source.remote.network.ApiService

class RemoteDataSource(private val api: ApiService) {
    suspend fun login() = api.login()
}