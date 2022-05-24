package com.example.ktor.feature.prices_list.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PricesResponse(
    @SerialName("data") val prices: List<PriceDto>
)

@Serializable
internal data class PriceDto(
    val id: String,
    val metrics: MetricsDto,
    val symbol: String
)

@Serializable
internal data class MetricsDto(
    @SerialName("market_data") val marketData: MarketDataDto
)

@Serializable
internal data class MarketDataDto(
    @SerialName("price_usd") val price: Double
)
