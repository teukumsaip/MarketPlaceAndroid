package com.teukkun.marketplace.ui.navigation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.teukkun.marketplace.core.data.repository.AppRepository

class HomeViewModel(private val repo: AppRepository) : ViewModel() {
    fun getHome() = repo.getHome().asLiveData()
}