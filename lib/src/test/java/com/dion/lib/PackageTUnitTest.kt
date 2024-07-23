package com.dion.lib
import com.dion.lib.func.PackageT
import org.junit.Assert.assertEquals
import org.junit.Test

class PackageTUnitTest {
    @Test
    fun testMessage_case1() {
        val t = PackageT()
        val r = t.printMessage()
        assertEquals("2, 1, 0", r)
    }
}