package com.example.ktor.data

import android.util.Log
import com.example.ktor.utils.Either
import timber.log.Timber

typealias ServerResponse<HttpFailure, SucessResponse> = Either<ApiResponseFailure<out HttpFailure>, SucessResponse>

sealed class ApiResponseFailure<HttpFailure> {
    data class HttpFailure<HttpFailure>(val code: Int, val errorBody: HttpFailure?) : ApiResponseFailure<HttpFailure>()
    object NetworkFailure : ApiResponseFailure<Nothing>()
    object SerializationFailure : ApiResponseFailure<Nothing>()
}

inline fun <HttpFailure, SuccessResponse> safeRequest(block: () -> SuccessResponse): ServerResponse<HttpFailure, SuccessResponse> {
    return try {
        Either.Right(block())
    } catch (e: Exception) {
        Timber.e(e)
        Either.Left(ApiResponseFailure.NetworkFailure)
    }
}
