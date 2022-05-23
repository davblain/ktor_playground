package com.example.ktor.prices_list.di

import androidx.lifecycle.ViewModel
import com.example.ktor.data.MessaryApi
import com.example.ktor.prices_list.data.PricesRepositoryImpl
import com.example.ktor.prices_list.feature.*
import com.example.ktor.utils.createFeature
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val pricesModule = module {

    single { MessaryApi(get()) }

    single<PricesRepository> { PricesRepositoryImpl(api = get()) }

    viewModel {
        PricesViewModel(
            createFeature(
                savedStateHandle = get(),
                featureParams = { pricesFeatureParams(pricesEffectHandler(get())) },
                getStateTransform = ::pricesViewStateTransform
            )
        )
    }
}

internal class PricesViewModel(feature: PricesFeature) : ViewModel(), PricesFeature by feature

