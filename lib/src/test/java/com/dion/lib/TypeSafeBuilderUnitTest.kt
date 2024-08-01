package com.dion.lib

import com.dion.lib.func.TypeSafeBuilder
import com.dion.lib.func.a.TypeSafeBuilderTwo
import com.dion.lib.func.b.TypeSafeBuilderThree
import com.dion.lib.func.c.TypeSafeBuilderFour
import com.dion.lib.func.d.TypeSafeBuilderFive
import com.dion.lib.func.fold
import com.dion.lib.func.run2
import com.dion.lib.func.runTransformation
import org.junit.Assert
import org.junit.Test
import java.lang.reflect.Type

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TypeSafeBuilderUnitTest {

    @Test
    fun testTypeSafeBuilder_case1() {
        print(TypeSafeBuilder().html.toString())

    }

    @Test
    fun testTypeSafeBuilder_case2() {
        val h = TypeSafeBuilder().createHtml {
            head {
                title { +"Hello" }
            }
            body {
                b { +"Newton" }
                h1 { +"Moon" }
            }
        }
        println(h)
    }

    @Test
    fun testTypeSafeBuilder_case3() {
        val h = TypeSafeBuilderTwo().html
        println(h)
    }

    @Test
    fun testTypeSafeBuilder_case4() {
        val h = TypeSafeBuilderThree().html
        print(h)
    }

    @Test
    fun testTypeSafeBuilder_case5() {
        val html = TypeSafeBuilderFour().html
        println(html)
    }

    @Test
    fun testTypeSafeBuilder_case6() {
        val html = TypeSafeBuilderFive().html
        print(html)
    }
}