package com.example.ktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ktor.feature.details.DetailsApi
import com.example.ktor.feature.prices_list.PricesApi
import com.example.ktor.ui.theme.KtorExampleTheme
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorExampleTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val priceApi = get<PricesApi>()
                    val detailsApi = get<DetailsApi>()
                    NavHost(navController = navController, startDestination = priceApi.pricesRoute) {
                        priceApi.registerPricesScreen(
                            this,
                            onClickItem = { navController.navigate(detailsApi.detailsRoute(it)) }
                        )
                        detailsApi.registerDetailsScreen(this)
                    }
                }
            }
        }
    }
}
