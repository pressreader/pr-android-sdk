package com.newspaperdirect.example.samplesdk

import android.app.Application
import com.newspaperdirect.sdk.PressReader
import com.newspaperdirect.sdk.PressReader.Companion.init
import com.newspaperdirect.sdk.analytics.PressReaderAnalyticsTracker

class GAppEx : Application() {
    override fun onCreate() {
        super.onCreate()
        val trackerList: MutableList<PressReaderAnalyticsTracker> = ArrayList()
        trackerList.add(CustomTracker())
        init(this, PressReader.Params(trackerList))
    }
}
