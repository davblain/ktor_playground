package com.example.ktor.feature.prices_list.feature

import family.amma.keemun.StateTransform
import family.amma.keemun.feature.Feature

internal typealias PricesFeature = Feature<PricesViewState, PricesMsg>

internal data class PricesViewState(val prices: List<PriceEntity>)

/** View State Transform. */
internal fun pricesViewStateTransform() = StateTransform<PricesState, PricesViewState> { state ->
    PricesViewState(state.prices)
}
