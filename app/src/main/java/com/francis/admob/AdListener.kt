package com.francis.admob

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.LoadAdError

open class AdListener : AdListener() {
    private val TAG by lazy { com.francis.admob.AdListener::class.java.simpleName }
    override fun onAdImpression() {
        super.onAdImpression()
        UiUtils.appErrorLog(TAG,"onAdImpression")
    }

    override fun onAdLeftApplication() {
        super.onAdLeftApplication()
        UiUtils.appErrorLog(TAG,"onAdLeftApplication")
    }

    override fun onAdClicked() {
        super.onAdClicked()
        UiUtils.appErrorLog(TAG,"onAdClicked")
    }

    override fun onAdFailedToLoad(p0: Int) {
        super.onAdFailedToLoad(p0)
        UiUtils.appErrorLog(TAG,"onAdFailedToLoad")
    }

    override fun onAdFailedToLoad(p0: LoadAdError?) {
        super.onAdFailedToLoad(p0)
        UiUtils.appErrorLog(TAG,"onAdFailedToLoad")
    }

    override fun onAdClosed() {
        super.onAdClosed()
        UiUtils.appErrorLog(TAG,"onAdClosed")
    }

    override fun onAdOpened() {
        super.onAdOpened()
        UiUtils.appErrorLog(TAG,"onAdOpened")
    }

    override fun onAdLoaded() {
        super.onAdLoaded()
        UiUtils.appErrorLog(TAG,"onAdLoaded")
    }
}