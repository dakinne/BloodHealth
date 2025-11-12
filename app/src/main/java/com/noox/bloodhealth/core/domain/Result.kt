package com.noox.bloodhealth.core.domain

sealed class Result<out E, out S> {

    data class Error<out E>(val value: E) : Result<E, Nothing>()
    data class Success<out S>(val value: S) : Result<Nothing, S>()

    fun fold(
        error: (E) -> Unit,
        success: (S) -> Unit
    ): Any = when(this) {
        is Error -> error(value)
        is Success -> success(value)
    }

}
