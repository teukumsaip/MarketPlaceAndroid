package com.teukkun.marketplace.ui.alamatToko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.teukkun.marketplace.core.data.repository.AlamatRepository
import com.teukkun.marketplace.core.data.source.model.AlamatToko

class AlamatTokoViewModel(private val repo: AlamatRepository) : ViewModel() {
    fun get() = repo.getAlamatToko().asLiveData()
    fun create(data: AlamatToko) = repo.createAlamatToko(data).asLiveData()
    fun update(data: AlamatToko) = repo.updateAlamatToko(data).asLiveData()
    fun delete(id: Int?) = repo.deleteAlamatToko(id).asLiveData()
}