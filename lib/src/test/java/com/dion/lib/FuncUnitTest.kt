package com.dion.lib

import com.dion.lib.func.fold
import com.dion.lib.func.runTransformation
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FuncUnitTest {
    //@Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    private var cb1: Combine1 = { acc, i ->
        print("acc = $acc, i = $i, ")
        acc + i
    }
    @Test
    fun fold_case1() {
        val a: List<Int> = listOf(1,2,3,4,5)
        //var r: Int = a.fold(0, A::combine1)
//        var r: Int = a.fold(0) {
//        acc, i ->
//            cb1.invoke(acc, i)
//        }
        var r: Int = a.fold(0, cb1::invoke)
        assertEquals(15,  r)
    }

    class A {
        companion object {
            fun combine1 (acc: Int, nextElement: Int): Int {
                print("acc = $acc, i = $nextElement, ")
                return acc + nextElement
            }
        }

    }

    @Test
    fun runTransformation_case1() {
        var repeatFun: String.(Int) -> String = {times -> this.repeat(times)}
        val twoParameter: (String, Int) -> String = repeatFun
        val r = runTransformation("hello", 4, repeatFun)
        assertEquals("hellohellohellohello", r)
    }
}

typealias Combine1 = (Int, Int) -> Int