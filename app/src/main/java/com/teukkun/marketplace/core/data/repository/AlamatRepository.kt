package com.teukkun.marketplace.core.data.repository

import com.teukkun.marketplace.core.data.source.local.LocalDataSource
import com.teukkun.marketplace.core.data.source.model.AlamatToko
import com.teukkun.marketplace.core.data.source.remote.RemoteDataSource
import com.teukkun.marketplace.util.defaultError
import com.inyongtisto.myhelper.extension.getErrorBody
import com.teukkun.marketplace.core.data.source.remote.network.Resource
import kotlinx.coroutines.flow.flow

class AlamatRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun getAlamatToko() = flow {
        emit(Resource.loading(null))
        try {
            remote.getAlamatToko().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data
                    emit(Resource.success(data))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun createAlamatToko(data: AlamatToko) = flow {
        emit(Resource.loading(null))
        try {
            remote.createAlamatToko(data).let {
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun updateAlamatToko(data: AlamatToko) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateAlamatToko(data).let {
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun deleteAlamatToko(id: Int?) = flow {
        emit(Resource.loading(null))
        try {
            remote.deleteAlamatToko(id).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data

                    emit(Resource.success(data))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }
}