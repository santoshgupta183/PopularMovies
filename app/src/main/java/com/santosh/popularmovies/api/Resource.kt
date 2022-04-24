package com.santosh.popularmovies.api

sealed class Resource<T> (
    val data: T?= null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(data: T? = null, errorMessage: String): Resource<T>(data, errorMessage)
    class Loading<T>(): Resource<T>()
}