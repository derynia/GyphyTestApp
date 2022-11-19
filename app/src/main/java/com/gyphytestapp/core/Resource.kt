package com.gyphytestapp.core

/*
 * Resource class provides error handling for data that we can receive from api
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String) : Resource<T>(message = message)
}