package com.example.ktor.prices_list.feature

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import family.amma.keemun.InitFeature
import family.amma.keemun.feature.FeatureParams
import kotlin.jvm.internal.Ref

typealias PricesFeatureParams = FeatureParams<PricesState, PricesMsg, PricesEffect>

/** Feature Params. */
fun pricesFeatureParams(
    effectHandler: PricesEffectHandler
): PricesFeatureParams = FeatureParams(
    init = init(),
    update = pricesUpdater(),
    effectHandler = effectHandler
)

private fun init() = InitFeature<PricesState, PricesEffect> { previous ->
    val state = previous ?: PricesState(prices = emptyList())
    state to setOf(PricesEffect.ObservePrices)
}

@Parcelize
data class PricesState(
    val prices: List<PriceEntity>
) : Parcelable

/** Доступные сообщения. */
sealed class PricesMsg

@Parcelize
data class PriceEntity(
    val id: String,
    val name: String,
    val price: Double
) : Parcelable


