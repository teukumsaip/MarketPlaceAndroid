package com.teukkun.marketplace.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.teukkun.marketplace.core.data.repository.AppRepository

class NavViewModel(val repo: AppRepository) : ViewModel() {
    fun getUser(id: Int) = repo.getUser(id).asLiveData()
}