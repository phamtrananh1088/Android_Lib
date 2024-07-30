package com.dion.lib
import android.content.Context
import android.location.Location
import com.dion.lib.func.Config
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.withSettings
import org.mockito.junit.MockitoJUnitRunner
import java.util.Calendar

private const val FAKE_STRING = "HELLO WORLD"

@RunWith(MockitoJUnitRunner::class)
class ConfigUnitTest {
    @Test
    fun testConfig_case1() {
        assertEquals("America/Los_Angeles", Config.timeZone.id)
        //assertEquals(0,Config.calendar.get(Calendar.DATE))
        println(String.format("%04d/%02d/%02d", Config.calendar.get(Calendar.YEAR), Config.calendar.get(Calendar.MONTH), Config.calendar.get(Calendar.DATE)))
        println(String.format("%02d:%02d", Config.calendar.get(Calendar.HOUR), Config.calendar.get(Calendar.MINUTE)))
        assertEquals(-8,Config.timeZone.rawOffset/ (3600*1000))
        //assertEquals(0, Config.timeZone.getOffset(Config.calendar.timeInMillis))
    }

    @Mock
    private  lateinit var mockLocation: Location

    @Test
    fun testConfig_location_case1() {
        mockLocation = mock<Location>(Location::class.java)
        mockLocation.latitude = 22.234
        mockLocation.longitude = 22.234
        Mockito.`when`(mockLocation.latitude).thenReturn(22.234)
        println(mockLocation.latitude)
        assertEquals(22.234, mockLocation.latitude, 0.01)
        Thread.sleep(1000)
    }

    @Mock
    private lateinit var mockContext: Context

    @Test
    fun readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
        val mockContext = mock<Context>(Context::class.java)
        `when`(mockContext.getString(R.string.name_label)).thenReturn(FAKE_STRING)
        // ...then the result should be the expected one.
        assertEquals(FAKE_STRING, mockContext.getString(R.string.name_label))
    }

}

