package com.dion.lib.func

class RetryableException(
    val actualCause: Throwable,
    private val retry: (cause: Throwable) -> Unit
) : Exception(actualCause)    {
    fun retry() {
        retry(actualCause)
    }
}