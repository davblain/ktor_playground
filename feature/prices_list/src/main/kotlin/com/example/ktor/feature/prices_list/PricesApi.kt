package com.example.ktor.feature.prices_list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ktor.feature.prices_list.di.PricesViewModel
import com.example.ktor.feature.prices_list.di.pricesModule
import io.ktor.client.*
import org.koin.androidx.compose.getStateViewModel
import org.koin.core.context.loadKoinModules

interface PricesApi {
    val pricesRoute: String
    fun registerPricesScreen(builder: NavGraphBuilder, navController: NavController)
}

internal class PricesApiImpl(deps: PricesDeps) : PricesApi {
    init {
        loadKoinModules(pricesModule(deps.httpClient))
    }

    override val pricesRoute: String = "prices"

    override fun registerPricesScreen(builder: NavGraphBuilder, navController: NavController) {
        builder.composable(pricesRoute) {
            PricesListUi(feature = getStateViewModel<PricesViewModel>())
        }
    }
}

fun PricesApi(deps: PricesDeps): PricesApi = PricesApiImpl(deps)

data class PricesDeps(
    val httpClient: HttpClient
)
