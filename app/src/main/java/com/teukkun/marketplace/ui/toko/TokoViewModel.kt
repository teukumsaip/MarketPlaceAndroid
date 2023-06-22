package com.teukkun.marketplace.ui.toko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.teukkun.marketplace.core.data.repository.AppRepository
import com.teukkun.marketplace.core.data.source.remote.request.CreateTokoRequest

class TokoViewModel(val repo: AppRepository) : ViewModel() {
    fun createToko(data: CreateTokoRequest) = repo.createToko(data).asLiveData()
}