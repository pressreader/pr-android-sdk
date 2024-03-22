package com.newspaperdirect.example.samplesdk

import android.app.Application
import com.newspaperdirect.sdk.PressReader
import com.newspaperdirect.sdk.analytics.AnalyticsTracker

class GAppEx : Application() {
    override fun onCreate() {
        super.onCreate()
        val trackerList: MutableList<AnalyticsTracker> = ArrayList()
        trackerList.add(CustomTracker())
        PressReader.init(this, PressReader.Params(trackerList))
    }
}
