package com.example.ktor.prices_list.feature

import family.amma.keemun.StateTransform
import family.amma.keemun.feature.Feature

typealias PricesFeature = Feature<PricesViewState, PricesMsg>

data class PricesViewState(val prices: List<PriceEntity>)

/** View State Transform. */
fun pricesViewStateTransform() = StateTransform<PricesState, PricesViewState> { state ->
    PricesViewState(state.prices)
}
