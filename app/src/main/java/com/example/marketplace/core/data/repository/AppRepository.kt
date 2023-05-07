package com.example.marketplace.core.data.repository

import android.util.Log
import com.example.marketplace.core.data.source.local.LocalDataSource
import com.example.marketplace.core.data.source.remote.RemoteDataSource
import kotlinx.coroutines.flow.flow

class AppRepository (val local :LocalDataSource, val remote:RemoteDataSource) {

    fun login() = flow {
        try {
            remote.login().let {
                if (it.isSuccessful){
                    logs("success" + body.toString)
                }else{
                    logs("Error:"+ "keterangan error")
                }
            }
        }catch (e:java.lang.Exception){
            Logs("login: error:" + e.message)
        }
    }
}