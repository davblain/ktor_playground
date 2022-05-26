package com.example.ktor.feature.details.di


import com.example.ktor.core.messari_api.MessariApi
import com.example.ktor.feature.details.DetailsViewModel
import com.example.ktor.feature.details.data.detailsProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal fun detailsModule(messariApi: MessariApi) = module {
    viewModel { (id: String) -> DetailsViewModel(id, detailsProvider = detailsProvider(messariApi)) }
}