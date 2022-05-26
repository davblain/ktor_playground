package com.example.ktor.di

import com.example.ktor.feature.details.DetailsApi
import com.example.ktor.feature.details.DetailsDeps
import org.koin.dsl.module

val detailsConfigurationModule = module {
    single { DetailsApi(DetailsDeps(get())) }
}
