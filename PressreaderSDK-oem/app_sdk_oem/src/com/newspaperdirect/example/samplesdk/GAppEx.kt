package com.newspaperdirect.example.samplesdk

import android.app.Application
import com.newspaperdirect.pressreader.android.oem.PressReaderOem
import com.newspaperdirect.sdk.analytics.AnalyticsTracker

class GAppEx : Application() {

    override fun onCreate() {
        super.onCreate()
        val trackerList: MutableList<AnalyticsTracker> = ArrayList()
        trackerList.add(CustomTracker())
        PressReaderOem.init(this, PressReaderOem.Params(trackerList))
    }
}
