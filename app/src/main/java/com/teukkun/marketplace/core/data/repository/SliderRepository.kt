package com.teukkun.marketplace.core.data.repository

import com.teukkun.marketplace.core.data.source.local.LocalDataSource
import com.teukkun.marketplace.core.data.source.remote.RemoteDataSource
import com.teukkun.marketplace.core.data.source.remote.request.SliderRequest
import com.teukkun.marketplace.util.defaultError
import com.inyongtisto.myhelper.extension.getErrorBody
import com.teukkun.marketplace.core.data.source.remote.network.Resource
import kotlinx.coroutines.flow.flow

class SliderRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun getSlider() = flow {
        emit(Resource.loading(null))
        try {
            remote.getSlider().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data

                    emit(Resource.success(data))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message.defaultError(), null))
        }
    }

    fun createSlider(data: SliderRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.createSlider(data).let {
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message.defaultError(), null))
        }
    }

    fun updateSlider(data: SliderRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateSlider(data).let {
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message.defaultError(), null))
        }
    }

    fun deleteSlider(id: Int?) = flow {
        emit(Resource.loading(null))
        try {
            remote.deleteSlider(id).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data

                    emit(Resource.success(data))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message.defaultError(), null))
        }
    }
}