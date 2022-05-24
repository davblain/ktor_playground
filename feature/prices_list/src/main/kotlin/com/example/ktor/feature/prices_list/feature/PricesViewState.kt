package com.example.ktor.feature.prices_list.feature

import family.amma.keemun.StateTransform
import family.amma.keemun.feature.Feature
import java.math.RoundingMode
import java.text.DecimalFormat

internal typealias PricesFeature = Feature<PricesViewState, PricesMsg>

internal data class PricesViewState(val prices: List<PriceItem>)

internal data class PriceItem(
    val id: String,
    val name: String,
    val priceValue: String
)

/** View State Transform. */
internal fun pricesViewStateTransform() = StateTransform<PricesState, PricesViewState> { state ->
    val priceItems = state.prices.map(::mapToPriceItem)
    PricesViewState(priceItems)
}

private fun mapToPriceItem(priceEntity: PriceEntity) =
    PriceItem(id = priceEntity.id, name = priceEntity.name, priceEntity.price.getPrettyFormatString())

private fun Double.getPrettyFormatString(): String {
    return DecimalFormat("#.##").apply {
        RoundingMode.CEILING
    }.format(this)
}
