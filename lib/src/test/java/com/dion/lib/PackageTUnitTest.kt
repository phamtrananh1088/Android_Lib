package com.dion.lib
import com.dion.lib.func.PackageT
import com.dion.lib.func.forEach2
import org.junit.Assert.assertEquals
import org.junit.Test

class PackageTUnitTest {
    @Test
    fun testMessage_case1() {
        val t = PackageT()
        val r = t.printMessage()
        assertEquals("2, 1, 0", r)
    }

    @Test
    fun testMessage2_case1() {
        val t = PackageT()
        val r = t.printMessage2()
        assertEquals("3, 2, 1", r)
    }

    @Test
    fun testMapNotNull_case1() {
        val t = PackageT()
        val r = t.mapNotNull()
        var s = r.sortedBy { it.x }.joinToString { it.x.toString() }
        assertEquals("1, 3, 5", s)
    }

    @Test
    fun testForEach2_case1() {
        var sum = 0
        fun a(): Boolean {
            val t = listOf(1,2,3,4,5)
            t.forEach2 {
                if(it > 2) {
                    return@forEach2
                }
                sum +=it
            }
            return true
        }
        assertEquals(true, a())
        assertEquals(3, sum)

        Runnable {
            var sum = 0
            listOf(1,2,3,4,5).forEach { sum += it }

        }
    }
}