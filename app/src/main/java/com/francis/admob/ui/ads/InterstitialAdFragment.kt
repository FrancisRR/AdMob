package com.francis.admob.ui.ads

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.francis.admob.AdListener
import com.francis.admob.R
import com.francis.admob.UiUtils
import com.francis.admob.base.AppController
import com.francis.admob.base.BaseFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.intertitial_ad_fragment.*

class InterstitialAdFragment : BaseFragment() {

    private val TAG by lazy { InterstitialAdFragment::class.java.simpleName }
    private var parentView: View? = null
    private var interstitialAd: InterstitialAd? = null
    private var handler: Handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parentView = inflater.inflate(R.layout.intertitial_ad_fragment, container, false)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        interstitialAd = InterstitialAd(AppController.instance)
        interstitialAd?.adUnitId =
            AppController.instance.resources.getString(R.string.interstitial_ad_unit_id)
        interstitialAd?.loadAd(AdRequest.Builder().build())

        interstitialAd?.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                interstitialAd?.loadAd(AdRequest.Builder().build())
            }
        }


        handler.postDelayed(runnable, 0)
        tvIntertitialAd.setOnClickListener {
            handler.postDelayed(runnable, 0)
        }
    }

    val runnable: Runnable = object : Runnable {
        override fun run() {
            if (interstitialAd?.isLoaded!!) {
                interstitialAd?.show()
            } else if (interstitialAd?.isLoading!!) {
                handler.postDelayed(this, 500)
                UiUtils.appErrorLog(TAG, "Delay is triggered")
            } else {
                UiUtils.appErrorLog(TAG, "Is loading")
            }

        }
    }


}