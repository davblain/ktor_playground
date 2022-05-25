package com.example.ktor.feature.prices_list.di

import androidx.lifecycle.ViewModel
import com.example.ktor.core.messari_api.MessariApi
import com.example.ktor.feature.prices_list.data.PricesRepositoryImpl
import com.example.ktor.feature.prices_list.feature.*
import com.example.ktor.feature.prices_list.feature.PricesFeature
import com.example.ktor.feature.prices_list.feature.PricesRepository
import com.example.ktor.feature.prices_list.feature.pricesEffectHandler
import com.example.ktor.feature.prices_list.feature.pricesFeatureParams
import com.example.ktor.feature.prices_list.feature.utils.createFeature
import kotlinx.coroutines.cancel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal fun pricesModule(messariApi: MessariApi) = module {

    single<PricesRepository> { PricesRepositoryImpl(api = messariApi) }
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

/* Пока не придумал как можно проще интегрировать keemun tea в стек koin+compose+navigation. */
internal class PricesViewModel(feature: PricesFeature) : ViewModel(), PricesFeature by feature {
    override fun onCleared() {
        scope.cancel()
    }
}
