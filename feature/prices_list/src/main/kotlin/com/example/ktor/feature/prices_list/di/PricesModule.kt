package com.example.ktor.feature.prices_list.di

import androidx.lifecycle.ViewModel
import com.example.ktor.feature.prices_list.data.MessaryApi
import com.example.ktor.feature.prices_list.data.PricesRepositoryImpl
import com.example.ktor.feature.prices_list.feature.*
import com.example.ktor.feature.prices_list.feature.PricesFeature
import com.example.ktor.feature.prices_list.feature.PricesRepository
import com.example.ktor.feature.prices_list.feature.pricesEffectHandler
import com.example.ktor.feature.prices_list.feature.pricesFeatureParams
import com.example.ktor.feature.prices_list.feature.utils.createFeature
import io.ktor.client.*
import kotlinx.coroutines.cancel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal fun pricesModule(httpClient: HttpClient) = module {

    single { MessaryApi(httpClient) }

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


internal class PricesViewModel(feature: PricesFeature) : ViewModel(), PricesFeature by feature {
    override fun onCleared() {
        scope.cancel()
    }
}
