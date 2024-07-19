package com.dion.lib

import com.dion.lib.func.fold
import com.dion.lib.func.run2
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

    @Test
    fun a() {
        val stringPlus: (String, String) -> String = String::plus
        val intPlus: Int.(Int) -> Int = Int::plus
        println(stringPlus.invoke("<-","->"))
        println(stringPlus("Hello, ", "World"))

        println(intPlus.invoke(1, 2))
        println(intPlus(1, 2))
        println(1.intPlus(2))

        assertEquals(4, 2.intPlus(2))
    }

    @Test
    fun b() {
        val sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
        val sum2 = { x: Int, y: Int -> x + y }
        
        assertEquals(4, sum2(2,2))
    }

    @Test
    fun c() {
        val a = listOf("John", "Peter", "Mary")
        a.run2 { x: String -> print(x) }

        assertEquals(4, 4) 
    }

    @Test
    fun d() {
        val ints = listOf(1,-1,0, 2)
        val r = ints.filter {
            val shouldFilter = it > 0
            shouldFilter
        }
        val r2 = ints.filter {
            val shouldFilter = it > 0
            return@filter shouldFilter
        }

        assertEquals(2, r.size)
        assertEquals(r.size, r2.size)
    }

    @Test
    fun e() {
        val strings = listOf("convention", "equals", "linq-", "lamda")
        val r = strings.filter { it.length == 5 }.sortedBy { it }.map { it.uppercase() }

        assertEquals("LINQ-", r[1])
    }

    @Test
    fun f() {
        fun(x: Int, y: Int): Int = x + y
        val a = listOf(-1, 1,2,3,4)
        //anonymous fun
        a.filter(fun(x) = x > 0)
        a.filter(fun(x): Boolean {
            return x > 0
        })

        println("return")
        //lambda fun
        val r = a.filter {x ->
            x > 0
            //return
        }
        print("OK")
        assertEquals(4, r.size)
    }

    @Test
    fun g() {
        val a = listOf(-1, 1, 2, 3, 4, 5)
        var sum = 0
        var sum2 = 0
        a.filter { it > 0 }.forEach {
            sum += it
        }

        a.filter { it > 0}.forEach(fun(x) {
            sum2 += x
        })
        assertEquals(sum, sum2)
    }

    @Test
    fun h() {
        val sum: Int.(Int) -> Int = { other -> plus(other) }
        val r = sum(10, 10)
        val sum2 = fun Int.(other: Int): Int = this + other
        assertEquals(20, r)
        assertEquals(20, 10.sum2(10))
    }

    @Test
    fun i() {
        class HTML {
            var x: Int = 0
            fun body(){
                x = 10
            }
        }
        fun html(init: HTML.() -> Unit): HTML {
            val html = HTML()
            html.init()
            return  html
        }

        val r = html { body() }
        assertEquals(10, r.x)

    }

}

typealias Combine1 = (Int, Int) -> Int
