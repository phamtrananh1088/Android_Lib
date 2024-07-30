package com.dion.lib.func

import android.location.Location
import android.location.LocationManager
import java.util.Calendar
import java.util.TimeZone

object Config {
    val timeZone: TimeZone = TimeZone.getTimeZone("America/Los_Angeles")
    val calendar: Calendar
        get() {
            return Calendar.getInstance(timeZone)
        }
    val location: Location = Location(LocationManager.GPS_PROVIDER)
}