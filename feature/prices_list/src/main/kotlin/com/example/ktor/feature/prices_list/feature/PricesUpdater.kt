package com.example.ktor.feature.prices_list.feature

import family.amma.keemun.Update

internal typealias PricesUpdate = Update<PricesState, PricesMsg, PricesEffect>

/** Update. */
internal fun pricesUpdater(): PricesUpdate {
    val internalUpdater = internalUpdater()
    val externalUpdater = externalUpdater()
    return PricesUpdate { msg, state ->
        when (msg) {
            is PricesExternalMsg -> externalUpdater(msg, state)
            is PricesInternalMsg -> internalUpdater(msg, state)
        }
    }
}

private fun internalUpdater() = Update<PricesState, PricesInternalMsg, PricesEffect> { msg, state ->
    when (msg) {
        is PricesInternalMsg.PricesUpdated ->
            state.copy(prices = msg.prices) to emptySet()
    }
}

private fun externalUpdater() = Update<PricesState, PricesExternalMsg, PricesEffect> { msg, state ->
    when (msg) {
        PricesExternalMsg.StartObservePrices ->
            state to setOf(PricesEffect.StartObservePrices)

        PricesExternalMsg.StopObservePrices ->
            state to setOf(PricesEffect.StopObservePrices)
    }
}

/** Сайд-эффекты. */
internal sealed class PricesEffect {
    object StartObservePrices : PricesEffect()
    object StopObservePrices : PricesEffect()
    object ObservePrices : PricesEffect()
}

/** Внешние сообщения. */
internal sealed class PricesExternalMsg : PricesMsg() {
    object StartObservePrices : PricesExternalMsg()
    object StopObservePrices : PricesExternalMsg()
}
