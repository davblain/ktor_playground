package com.example.ktor.prices_list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ktor.prices_list.di.PricesViewModel
import com.example.ktor.prices_list.di.pricesModule
import org.koin.androidx.compose.getStateViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.loadKoinModules

interface PricesApi {
    val pricesRoute: String
    fun registerPricesScreen(builder: NavGraphBuilder, navController: NavController)
}

private object PricesApiImpl : PricesApi {
    init {
        loadKoinModules(pricesModule)
    }

    override val pricesRoute: String = "prices"

    override fun registerPricesScreen(builder: NavGraphBuilder, navController: NavController) {
        builder.composable(pricesRoute) {
            PricesListUi(feature = getStateViewModel<PricesViewModel>())
        }
    }

}

fun PricesApi(): PricesApi = PricesApiImpl
