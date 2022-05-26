package com.example.ktor.feature.prices_list.feature

import family.amma.keemun.EffectHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

internal typealias PricesEffectHandler = EffectHandler<PricesEffect, PricesMsg>

internal fun pricesEffectHandler(
    repository: PricesRepository
): PricesEffectHandler {
    val updatesState = MutableStateFlow(UpdateState.Stopped)
    return PricesEffectHandler { effect, dispatch ->
        when (effect) {
            PricesEffect.StartObservePrices -> {
                updatesState.value = UpdateState.InProcess
            }
            PricesEffect.ObservePrices -> {
                updatesState.filter { it == UpdateState.InProcess }
                    .onEach { dispatch(PricesInternalMsg.PricesUpdated(repository.getPrices())) }
                    .onEach { delay(5000) }
                    .collect()
            }
            PricesEffect.StopObservePrices -> updatesState.value = UpdateState.Stopped
        }
    }
}

enum class UpdateState {
    Stopped, InProcess
}

/** Внутренние сообщения. */
internal sealed class PricesInternalMsg : PricesMsg() {
    data class PricesUpdated(val prices: List<PriceEntity>) : PricesInternalMsg()
}

internal interface PricesRepository {
    suspend fun getPrices(): List<PriceEntity>
}
