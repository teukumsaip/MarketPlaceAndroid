package com.teukkun.marketplace.core.di

import com.teukkun.marketplace.ui.alamatToko.AlamatTokoViewModel
import com.teukkun.marketplace.ui.category.CategoryViewModel
import com.teukkun.marketplace.ui.navigation.NavViewModel
import com.teukkun.marketplace.ui.navigation.home.HomeViewModel
import com.teukkun.marketplace.ui.product.ProductViewModel
import com.teukkun.marketplace.ui.slider.SliderViewModel
import com.teukkun.marketplace.ui.toko.TokoViewModel
import com.teukkun.marketplace.core.data.repository.BaseViewModel
import com.teukkun.marketplace.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { TokoViewModel(get()) }
    viewModel { NavViewModel(get()) }
    viewModel { AlamatTokoViewModel(get()) }
    viewModel { ProductViewModel(get()) }
    viewModel { CategoryViewModel(get()) }
    viewModel { BaseViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { SliderViewModel(get()) }
}