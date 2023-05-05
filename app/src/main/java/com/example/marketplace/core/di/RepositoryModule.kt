package com.example.marketplace.core.di

import com.example.marketplace.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get())}
}