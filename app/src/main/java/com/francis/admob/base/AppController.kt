package com.francis.admob.base

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.francis.admob.AdHandler
import com.francis.admob.R
import com.francis.admob.UiUtils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener

class AppController : Application(), LifecycleObserver {

    private val TAG: String? = AppController::class.java.simpleName

    companion object {
        internal lateinit var instance: AppController
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        AdHandler.initializeAdMobSDK()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun appForeground() {
        UiUtils.appErrorLog(TAG, "App in foreground")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun appBackground() {
        UiUtils.appErrorLog(TAG, "App in background")
    }

}