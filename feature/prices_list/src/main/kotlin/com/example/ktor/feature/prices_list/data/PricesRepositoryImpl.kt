package com.example.ktor.feature.prices_list.data

import com.example.ktor.core.std.fold
import com.example.ktor.core.std.map
import com.example.ktor.feature.prices_list.data.models.PriceDto
import com.example.ktor.feature.prices_list.feature.PriceEntity
import com.example.ktor.feature.prices_list.feature.PricesRepository

internal class PricesRepositoryImpl(private val api: MessaryApi) : PricesRepository {

    override suspend fun getPrices(): List<PriceEntity> {
        return api.getPrices()
            .map { it.prices.mapToEntity() }
            .fold(
                ifRight = { it },
                ifLeft = { emptyList() }
            )
    }

    private fun List<PriceDto>.mapToEntity(): List<PriceEntity> {
        return map { priceDto ->
            PriceEntity(id = priceDto.id, name = priceDto.symbol, price = priceDto.metrics.marketData.price)
        }
    }
}
