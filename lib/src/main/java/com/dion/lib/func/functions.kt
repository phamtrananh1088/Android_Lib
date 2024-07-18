package com.dion.lib.func

/**
 * Returns the accumulate value.
 *
 *
 */
fun <T, R> Collection<T>.fold(
    initial: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

/**
 * transform text.
 *
 *
 */
fun runTransformation (s: String, i: Int, f: (String, Int) -> String): String {
    return f(s,i)
}