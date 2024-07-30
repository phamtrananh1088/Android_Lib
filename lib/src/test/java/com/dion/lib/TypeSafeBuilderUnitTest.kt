package com.dion.lib

import com.dion.lib.func.TypeSafeBuilder
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

}