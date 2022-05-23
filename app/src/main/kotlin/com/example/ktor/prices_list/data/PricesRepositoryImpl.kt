package com.example.ktor.prices_list.data

import com.example.ktor.data.MessaryApi
import com.example.ktor.data.PriceDto
import com.example.ktor.prices_list.feature.PriceEntity
import com.example.ktor.prices_list.feature.PricesRepository
import com.example.ktor.utils.fold
import com.example.ktor.utils.map

class PricesRepositoryImpl(private val api: MessaryApi) : PricesRepository {

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
