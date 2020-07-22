package com.francis.admob.ui.ads

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.francis.admob.R
import com.francis.admob.UiUtils
import com.francis.admob.base.BaseFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import kotlinx.android.synthetic.main.rewarededad_fragment.*

class RewardedAdFragment : BaseFragment() {

    private val TAG by lazy { RewardedAdFragment::class.java.simpleName }
    private var parentView: View? = null
    private var rewardedVideoAd: RewardedVideoAd? = null
    private var handler: Handler? = null
    private var rewardedAdFailToInitialization: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parentView = inflater.inflate(R.layout.rewarededad_fragment, container, false)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler = Handler()
        initializeTheAd()
        rewardedAdRequest()
        startRewardedAd()

        tvRewardedAd.setOnClickListener {
            startRewardedAd()
        }


    }

    val runnable: Runnable = object : Runnable {
        override fun run() {
            if (rewardedVideoAd?.isLoaded!!) {
                rewardedVideoAd?.show()
            } else if (!rewardedAdFailToInitialization) {
                handler?.postDelayed(this, 500)
                UiUtils.appErrorLog(TAG, "Delay applied for 500 MS")
            } else {
                handler?.removeCallbacks(this)
                UiUtils.appErrorLog(TAG, "Runnable stopped")
            }
        }
    }

    private fun startRewardedAd() {
        handler?.postDelayed(runnable, 0)
    }

    private fun rewardedAdRequest() {
        rewardedAdFailToInitialization = false
        rewardedVideoAd?.loadAd(
            resources.getString(R.string.rewarded_ad_unit_id),
            AdRequest.Builder().build()
        )
    }


    private fun initializeTheAd() {
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context)
        rewardedVideoAd?.rewardedVideoAdListener = object : RewardedVideoAdListener {
            override fun onRewardedVideoAdClosed() {
                UiUtils.appErrorLog(TAG, "onRewardedVideoAdClosed")
                rewardedAdRequest()
            }

            override fun onRewardedVideoAdLeftApplication() {
                UiUtils.appErrorLog(TAG, "onRewardedVideoAdLeftApplication")
            }

            override fun onRewardedVideoAdLoaded() {
                UiUtils.appErrorLog(TAG, "onRewardedVideoAdLoaded")
            }

            override fun onRewardedVideoAdOpened() {
                UiUtils.appErrorLog(TAG, "onRewardedVideoAdOpened")
            }

            override fun onRewardedVideoCompleted() {
                UiUtils.appErrorLog(TAG, "onRewardedVideoCompleted")
            }

            override fun onRewarded(r: RewardItem?) {
                UiUtils.appErrorLog(TAG, "onRewarded")
                UiUtils.appErrorLog(TAG, "Type: ${r?.type} Amount: ${r?.amount}")
            }

            override fun onRewardedVideoStarted() {
                UiUtils.appErrorLog(TAG, "onRewardedVideoStarted")
            }

            override fun onRewardedVideoAdFailedToLoad(p0: Int) {
                rewardedAdFailToInitialization = true
                UiUtils.appErrorLog(TAG, "onRewardedVideoAdFailedToLoad")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        rewardedVideoAd?.pause(context)
        UiUtils.appErrorLog(TAG, "onPause")
    }

    override fun onResume() {
        super.onResume()
        rewardedVideoAd?.resume(context)
        UiUtils.appErrorLog(TAG, "onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        rewardedVideoAd?.destroy(context)
        UiUtils.appErrorLog(TAG, "onDestroy")
        handler?.removeCallbacks(runnable)
    }
}