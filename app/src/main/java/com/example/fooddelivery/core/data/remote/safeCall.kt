package com.example.fooddelivery.core.data.remote

import com.example.fooddelivery.core.domain.util.DataError
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext
import com.example.fooddelivery.core.domain.util.Result

suspend fun <T> safeCall(
    request: suspend () -> T
): Result<T, DataError> {
    try {
        val response = request()
        return Result.Success(response)
    } catch(e: Exception) {
        coroutineContext.ensureActive()
        return mapExceptionToResult(e)
    }
}