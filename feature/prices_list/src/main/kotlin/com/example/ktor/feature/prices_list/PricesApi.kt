package com.example.ktor.feature.prices_list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ktor.core.messari_api.MessariApi
import com.example.ktor.feature.prices_list.di.PricesViewModel
import com.example.ktor.feature.prices_list.di.pricesModule
import org.koin.androidx.compose.getStateViewModel
import org.koin.core.context.loadKoinModules

typealias Id = String

interface PricesApi {
    val pricesRoute: String
    fun registerPricesScreen(builder: NavGraphBuilder, onClickItem: (Id) -> Unit)
}

internal class PricesApiImpl(deps: PricesDeps) : PricesApi {
    init {
        loadKoinModules(pricesModule(deps.messariApi))
    }

    override val pricesRoute: String = "prices"

    override fun registerPricesScreen(builder: NavGraphBuilder, onClickItem: (Id) -> Unit) {
        builder.composable(pricesRoute) {
            PricesListUi(feature = getStateViewModel<PricesViewModel>(), onClickItem = { onClickItem(it.id) })
        }
    }
}

fun PricesApi(deps: PricesDeps): PricesApi = PricesApiImpl(deps)

data class PricesDeps(
    val messariApi: MessariApi
)
