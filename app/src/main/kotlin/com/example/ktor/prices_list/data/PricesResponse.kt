package com.example.ktor.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PricesResponse(
    @SerialName("data") val prices: List<PriceDto>
)

@Serializable
data class PriceDto(
    val id: String,
    val metrics: MetricsDto,
    val symbol: String
)

@Serializable
data class MetricsDto(
    @SerialName("market_data") val marketData: MarketDataDto
)

@Serializable
data class MarketDataDto(
    @SerialName("price_usd") val price: Double
)