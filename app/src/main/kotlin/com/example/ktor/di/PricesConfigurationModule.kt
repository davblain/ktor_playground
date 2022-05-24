package com.example.ktor.di

import com.example.ktor.feature.prices_list.PricesApi
import com.example.ktor.feature.prices_list.PricesDeps
import org.koin.dsl.module

val pricesConfigurationModule = module {
    single { PricesApi(PricesDeps(get())) }
}
