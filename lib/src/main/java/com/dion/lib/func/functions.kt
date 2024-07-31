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
* run with lambda
*
*
*/
fun<T> Collection<T>.run2(
    action: (acc: T) -> Unit
    ): Unit {
    for (element: T in this) {
        action(element)
    }
}

/**
 * transform text.
 *
 *
 */
fun runTransformation (s: String, i: Int, f: (String, Int) -> String): String {
    return f(s,i)
}

fun getMax(a: Int, b: Int): Int {
    var max = b;
    if (a > b) max = a;
    return max;
}

fun getMax2(a: Int, b: Int): Int {
    var max: Int
    if(a < b) {
        max = b
    } else {
        max = a
    }
    return max
}
fun getMax3(a: Int, b: Int): Int {
    var max: Int
    max = if(a < b) b else a
    return  max
}

fun getMax5(a : Int, b: Int): Int {
    var max: Int
    max = if(a < b) {
        print(b)
        b
    } else {
        print(a)
        a
    }
    return max
}
fun getMax6(a: Int, b: Int): Int {
    var litmit = 1
    var maxOrLitmit = if(litmit > a) litmit else if(a > b) a else b
    return  maxOrLitmit
}

fun getMax7(a : Int, b: Int): Int {
    var max: Int = a
    if(a < b) max = b
    //
    if(a < b) max = b
    else max = a

    //
    max = if(a < b) b else a
    //
    max = if(a < b) {
        print("b")
        b
    } else {
        print(a)
        a
    }
    return  max
}
fun switch() {
    val a = 1
    when(a) {
        1 -> print("a == 1")
        2 -> print("a == 2")
        else -> print("a is neither 1 nor 2")
    }
    var x = 2
    when(a) {
        1-> print("x == 1")
        2 -> print("x == 2")
         else -> print("x is neither 1 nor 2")
    }
}
fun getMessage(x: Int): String {
    var s: String
    s = when(x) {
        1 -> "x == 1"
        2 -> "x == 2"
        else -> "x is neither 1 nor 2"
    }
    when(x) {
        1 -> s = "x == 1"
        2 -> s = "x == 2"
        else -> s = "x is neither 1 nor 2"
    }
    return s
}
enum class Bit {
    Zero, One

}
fun getMessage2(bit: Bit): String {
    return when(bit) {
        Bit.Zero -> "Zero"
        Bit.One -> "One"
    }
}
