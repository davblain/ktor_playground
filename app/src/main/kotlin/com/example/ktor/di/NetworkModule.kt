package com.example.ktor.di

import com.example.ktor.data.MessaryApi
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json as SerializationJson
import org.koin.dsl.module

const val TIME_OUT = 60_000

val networkModule = module {
    single { configureMessaryClient() }
}

private fun configureMessaryClient(): HttpClient {
    return HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                SerializationJson {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }
        install(Logging)
        install(DefaultRequest) {
            header("x-messari-api-key", "44926959-5c77-4b81-8ffa-43920d45d57f")
        }
    }
}