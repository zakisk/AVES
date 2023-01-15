package com.aves.domain.state


sealed class DataState<out T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : DataState<T>(data = data)

    class Error<T>(message: String? = null) : DataState<T>(message = message)

    class Loading<T> : DataState<T>()

}
