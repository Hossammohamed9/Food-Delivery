package com.example.fooddelivery.core.data.remote

import com.example.fooddelivery.core.domain.util.DataError
import com.example.fooddelivery.core.domain.util.Result
import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T> mapExceptionToResult(exception: Exception): Result<T, DataError> {
    return when (exception) {
        is HttpException -> {
            if (exception.code() == 500) {
                Result.Error(DataError.SERVER_ERROR)
            } else {
                Result.Error(DataError.UNKNOWN)
            }
        }

        is UnknownHostException -> Result.Error(DataError.NO_INTERNET)
        is SocketTimeoutException -> Result.Error(DataError.REQUEST_TIMEOUT)
        is JsonParseException -> Result.Error(DataError.PARSING_ERROR)
        is ConnectException -> Result.Error(DataError.NO_INTERNET)
        else -> Result.Error(DataError.UNKNOWN)
    }
}