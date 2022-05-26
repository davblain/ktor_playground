package com.example.ktor.feature.details.data

import com.example.ktor.core.messari_api.MessariApi
import com.example.ktor.core.network.ApiResponseFailure
import com.example.ktor.core.std.Either
import com.example.ktor.core.std.bimap
import com.example.ktor.feature.details.DetailsEntity
import com.example.ktor.feature.details.DetailsLoadingFailure

typealias DetailsProvider = suspend (String) -> Either<DetailsLoadingFailure, DetailsEntity>

internal fun detailsProvider(messariApi: MessariApi): DetailsProvider = { id: String ->
    messariApi.getCryptoProfile(id).bimap(
        leftOperation = {
            when (it) {
                ApiResponseFailure.NetworkFailure -> DetailsLoadingFailure.NoInternet
                else -> DetailsLoadingFailure.Other
            }
        },
        rightOperation = {
            DetailsEntity(it.data.profile.general.overview.details)
        }
    )
}
