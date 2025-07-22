package com.example.fooddelivery.core.domain.util

enum class DataError: Error {
    REQUEST_TIMEOUT,
    NO_INTERNET,
    SERVER_ERROR,
    PARSING_ERROR,
    UNKNOWN,
    DATABASE_ERROR
}