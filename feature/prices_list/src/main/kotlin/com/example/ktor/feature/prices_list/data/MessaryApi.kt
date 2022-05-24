package com.example.ktor.feature.prices_list.data

import com.example.ktor.core.network.ServerResponse
import com.example.ktor.core.network.safeRequest
import com.example.ktor.feature.prices_list.data.models.PricesResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MessaryApiRoutes {
    private const val BASE_URL = "https://data.messari.io/api/v2"
    const val ASSESTS = "$BASE_URL/assets"
}

internal class MessaryApi(private val client: HttpClient) {

    suspend fun getPrices(): ServerResponse<Unit, PricesResponse> {
        return withContext(Dispatchers.IO) {
            safeRequest {
                client.get {
                    url(MessaryApiRoutes.ASSESTS)
                    parameter("fields", "id,slug,symbol,metrics/market_data/price_usd")
                }
            }
        }
    }
}
