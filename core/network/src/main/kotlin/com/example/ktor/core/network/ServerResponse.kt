package com.example.ktor.core.network

import com.example.ktor.core.std.Either
import io.ktor.client.call.*
import io.ktor.client.features.*
import kotlinx.serialization.SerializationException
import timber.log.Timber
import java.io.IOException

typealias ServerResponse<HttpFailure, SuccessResponse> = Either<ApiResponseFailure<out HttpFailure>, SuccessResponse>

sealed class ApiResponseFailure<HttpFailure> {
    data class HttpFailure<HttpFailure>(val code: Int, val errorBody: HttpFailure?) : ApiResponseFailure<HttpFailure>()
    object NetworkFailure : ApiResponseFailure<Nothing>()
    object SerializationFailure : ApiResponseFailure<Nothing>()
}

suspend inline fun <reified HttpFailure, SuccessResponse> safeRequest(block: () -> SuccessResponse): ServerResponse<HttpFailure, SuccessResponse> {
    return try {
        Either.Right(block())
    } catch (e: ClientRequestException) {
        Timber.e(e)
        Either.Left(ApiResponseFailure.HttpFailure(e.response.status.value, e.errorBody()))
    } catch (e: ServerResponseException) {
        Timber.e(e)
        Either.Left(ApiResponseFailure.HttpFailure(e.response.status.value, e.errorBody()))
    } catch (e: IOException) {
        Timber.e(e)
        Either.Left(ApiResponseFailure.NetworkFailure)
    } catch (e: SerializationException) {
        Timber.e(e)
        Either.Left(ApiResponseFailure.SerializationFailure)
    }
}

suspend inline fun <reified E> ResponseException.errorBody(): E? =
    try {
        response.receive()
    } catch (e: SerializationException) {
        null
    }
