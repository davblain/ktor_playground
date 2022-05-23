package com.example.ktor.prices_list.feature

import family.amma.keemun.Update

typealias PricesUpdate = Update<PricesState, PricesMsg, PricesEffect>

/** Update. */
fun pricesUpdater(): PricesUpdate {
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
        is PricesInternalMsg.GetPricesResult ->
            state.copy(prices = msg.prices) to emptySet()
    }
}

private fun externalUpdater() = Update<PricesState, PricesExternalMsg, PricesEffect> { msg, state ->
    state to emptySet()
}

/** Сайд-эффекты. */
sealed class PricesEffect {
    object ObservePrices : PricesEffect()
}

/** Внешние сообщения. */
sealed class PricesExternalMsg : PricesMsg()
