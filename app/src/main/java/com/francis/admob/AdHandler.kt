package com.francis.admob

import com.francis.admob.base.AppController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener

object AdHandler {

    private val TAG by lazy { AdHandler::class.java.simpleName }

    internal fun initializeAdMobSDK() {
        MobileAds.initialize(
            AppController.instance, OnInitializationCompleteListener { p0: InitializationStatus? ->
                UiUtils.appErrorLog(
                    TAG,
                    "Ad Mob initialized succesfully"
                )

            })
    }


}