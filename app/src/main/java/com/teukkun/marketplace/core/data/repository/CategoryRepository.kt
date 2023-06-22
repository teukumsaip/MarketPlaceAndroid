package com.teukkun.marketplace.core.data.repository

import com.teukkun.marketplace.core.data.source.local.LocalDataSource
import com.teukkun.marketplace.core.data.source.remote.RemoteDataSource
import com.teukkun.marketplace.core.data.source.remote.request.CategoryRequest
import com.teukkun.marketplace.util.defaultError
import com.inyongtisto.myhelper.extension.getErrorBody
import com.teukkun.marketplace.core.data.source.remote.network.Resource
import kotlinx.coroutines.flow.flow

class CategoryRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun getCategory() = flow {
        emit(Resource.loading(null))
        try {
            remote.getCategory().let {
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

    fun createCategory(data: CategoryRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.createCategory(data).let {
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

    fun updateCategory(data: CategoryRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateCategory(data).let {
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

    fun deleteCategory(id: Int?) = flow {
        emit(Resource.loading(null))
        try {
            remote.deleteCategory(id).let {
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