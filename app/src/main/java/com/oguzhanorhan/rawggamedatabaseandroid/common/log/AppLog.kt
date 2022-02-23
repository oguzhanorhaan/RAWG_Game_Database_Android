package com.oguzhanorhan.rawggamedatabaseandroid.common.log

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object AppLog {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    fun initFirebase(context: Context) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    fun sendEvent(appEventType: AppEventType) {

        firebaseAnalytics.logEvent(
            appEventType.javaClass.name,
            Bundle().apply {
                this.putString(appEventType.key, appEventType.param)
            }
        )
    }
}
