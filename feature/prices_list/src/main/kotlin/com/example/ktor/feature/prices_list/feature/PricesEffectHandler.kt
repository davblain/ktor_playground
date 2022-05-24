package com.example.ktor.feature.prices_list.feature

import family.amma.keemun.EffectHandler
import kotlinx.coroutines.delay

internal typealias PricesEffectHandler = EffectHandler<PricesEffect, PricesMsg>

internal fun pricesEffectHandler(
    repository: PricesRepository
) = PricesEffectHandler { effect, dispatch ->
    when (effect) {
        PricesEffect.ObservePrices ->
            while (true) {
                val prices = repository.getPrices()
                dispatch(PricesInternalMsg.PricesUpdated(prices))
                delay(5000)
            }
    }
}

/** Внутренние сообщения. */
internal sealed class PricesInternalMsg : PricesMsg() {
    data class PricesUpdated(val prices: List<PriceEntity>) : PricesInternalMsg()
}

internal interface PricesRepository {
    suspend fun getPrices(): List<PriceEntity>
}
