package com.teukkun.marketplace.core.data.repository

import com.teukkun.marketplace.core.data.source.local.LocalDataSource
import com.teukkun.marketplace.core.data.source.remote.RemoteDataSource
import com.teukkun.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.teukkun.marketplace.core.data.source.remote.request.LoginRequest
import com.teukkun.marketplace.core.data.source.remote.request.RegisterRequest
import com.teukkun.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.teukkun.marketplace.util.defaultError
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.toJson
import com.teukkun.marketplace.core.data.source.remote.network.Resource
import com.teukkun.marketplace.util.Prefs
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun login(data: LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    logs("user:" + user.toJson())
                    Prefs.setUser(user)
                    Prefs.token = user?.token ?: "tokenError"
                    emit(Resource.success(user))
                    logs("succes:" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                    logs("Error:" + "keteragan error")
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error:" + e.message)
        }
    }

    fun register(data: RegisterRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.register(data).let {
                if (it.isSuccessful) {
                    Prefs.isLogin = true
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(user))
                    logs("succes:" + body.toString())
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                    logs("Error:" + "keteragan error")
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
            logs("Error:" + e.message)
        }
    }

    fun updateUser(data: UpdateProfileRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateUser(data).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(user))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) = flow {
        emit(Resource.loading(null))
        try {
            remote.uploadUser(id, fileImage).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(user))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun createToko(data: CreateTokoRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.createToko(data).let {
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

    fun getUser(id: Int? = null) = flow {
        emit(Resource.loading(null))
        try {
            remote.getUser(id).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data
                    Prefs.setUser(user)
                    emit(Resource.success(user))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun uploadImage(path: String, fileImage: MultipartBody.Part? = null) = flow {
        emit(Resource.loading(null))
        try {
            remote.uploadImage(path, fileImage).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val fileName = body?.data
                    emit(Resource.success(fileName))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message.defaultError(), null))
        }
    }

    fun getHome() = flow {
        emit(Resource.loading(null))
        try {
            remote.getHome().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val homeData = body?.data
                    emit(Resource.success(homeData))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }
}