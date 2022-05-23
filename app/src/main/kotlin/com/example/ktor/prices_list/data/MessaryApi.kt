package com.example.ktor.data

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.util.*

object MessaryApiRoutes {
    private const val BASE_URL = "https://data.messari.io/api/v2"
    const val ASSESTS = "$BASE_URL/assets"
}

class MessaryApi(private val client: HttpClient) {

    suspend fun getPrices(): ServerResponse<Unit, PricesResponse> {
        return safeRequest {
            client.get{
                url(MessaryApiRoutes.ASSESTS)
                parameter("fields", "id,slug,symbol,metrics/market_data/price_usd")
            }
        }

    }
}

