package com.segment.analytics.kotlin.destinations

import android.app.Application
import com.segment.analytics.kotlin.destinations.plugins.*
import com.segment.analytics.kotlin.android.Analytics
import com.segment.analytics.kotlin.core.Analytics
import java.util.concurrent.Executors

class MainApplication : Application() {
    companion object {
        lateinit var analytics: Analytics
    }

    override fun onCreate() {
        super.onCreate()

        analytics = Analytics(BuildConfig.SEGMENT_WRITE_KEY, applicationContext) {
            this.collectDeviceId = true
            this.trackApplicationLifecycleEvents = true
            this.trackDeepLinks = true
            this.flushAt = 1
            this.flushInterval = 0
        }

        // A random webhook url to view your events
        analytics.add(
            WebhookPlugin(
                "https://webhook.site/387c1740-f919-4446-a26e-a9a01ed28c8a",
                Executors.newSingleThreadExecutor()
            )
        )

        // Try out Intercom destination
        analytics.add(IntercomDestination(this))

    }
}