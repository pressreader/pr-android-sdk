package com.newspaperdirect.example.samplesdk

import android.app.Application
import com.newspaperdirect.sdk.analytics.AnalyticsTracker
import com.newspaperdirect.sdkfull.PressReader

class GAppEx : Application() {
    override fun onCreate() {
        super.onCreate()
        val trackerList: MutableList<AnalyticsTracker> = ArrayList()
        trackerList.add(CustomTracker())
        PressReader.init(this, PressReader.Params(trackerList))
    }
}
