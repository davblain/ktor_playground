package com.example.ktor.prices_list.feature

import family.amma.keemun.EffectHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

typealias PricesEffectHandler = EffectHandler<PricesEffect, PricesMsg>

fun pricesEffectHandler(
    repository: PricesRepository
) = PricesEffectHandler { effect, dispatch ->
    when (effect) {
        PricesEffect.ObservePrices ->
            while (true) {
                val prices = repository.getPrices()
                dispatch(PricesInternalMsg.GetPricesResult(prices))
                delay(5000)
            }
    }
}

/** Внутренние сообщения. */
sealed class PricesInternalMsg : PricesMsg() {
    data class GetPricesResult(val prices: List<PriceEntity>) : PricesInternalMsg()
}

interface PricesRepository {
    suspend fun getPrices(): List<PriceEntity>
}
