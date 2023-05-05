package com.example.marketplace.core.data.source.remote.network

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // @Headers(API)
    @POST("login")
    suspend fun login(
        //@BODY user: User
    ): Response<RequestBody>



}