package com.example.ktor.core.messari_api

import PricesResponse
import com.example.ktor.core.messari_api.models.CryptoProfileResponse
import com.example.ktor.core.network.ServerResponse
import com.example.ktor.core.network.safeRequest
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal object MessaryApiRoutes {
    private const val BASE_URL = "https://data.messari.io/api/v2"
    internal const val ASSESTS = "$BASE_URL/assets"
    fun getProfileUrl(id: String) = "$ASSESTS/$id/profile"
}

class MessariApi(private val client: HttpClient) {

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

    suspend fun getCryptoProfile(id: String): ServerResponse<Unit, CryptoProfileResponse> {
        return withContext(Dispatchers.IO) {
            safeRequest {
                client.get {
                    url(MessaryApiRoutes.getProfileUrl(id))
                    parameter("fields", "profile/general/overview,background")
                }
            }
        }
    }
}
