package com.teukkun.marketplace.core.data.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import okhttp3.MultipartBody

class BaseViewModel(private val repo: AppRepository) : ViewModel() {
    fun upload(path: String, fileImage: MultipartBody.Part? = null) =
        repo.uploadImage(path, fileImage).asLiveData()
}