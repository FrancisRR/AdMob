package com.francis.admob.ui.ads

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.francis.admob.R
import com.francis.admob.UiUtils
import com.francis.admob.base.BaseFragment
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class RewardedAdNewApiFragment : BaseFragment() {

    private val TAG by lazy { RewardedAdNewApiFragment::class.java.simpleName }
    private var parentView: View? = null
    private var rewardedAd: RewardedAd? = null
    private lateinit var handler: Handler
    private var rewardedAdFailToInitialization: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parentView = inflater.inflate(R.layout.rewardedad_new_api_fragment, container, false)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler = Handler()
        initializeAd()
        showAction()
    }

    private fun showAction() {
        handler.postDelayed(runnable, 0)
    }

    private fun initializeAd() {
        rewardedAd = RewardedAd(context, resources.getString(R.string.rewarded_ad_new_api_unit_id))
        rewardedAd?.loadAd(AdRequest.Builder().build(), object : RewardedAdLoadCallback() {
            override fun onRewardedAdFailedToLoad(p0: LoadAdError?) {
                super.onRewardedAdFailedToLoad(p0)
                rewardedAdFailToInitialization = true
                UiUtils.appErrorLog(TAG, "Failed to load")

            }

            override fun onRewardedAdLoaded() {
                super.onRewardedAdLoaded()
                rewardedAdFailToInitialization = false
                UiUtils.appErrorLog(TAG, "Loaded successfully")
            }
        })
    }

    val runnable: Runnable = object : Runnable {
        override fun run() {
            if (rewardedAd?.isLoaded!!) {
                showTheAd()
            } else if (!rewardedAdFailToInitialization) {
                UiUtils.appErrorLog(TAG, "Post delay applied")
                handler.postDelayed(this, 1000)
            } else {
                UiUtils.appErrorLog(TAG, "call back has been removed")
                handler.removeCallbacks(this)
            }
        }
    }


    private fun showTheAd() {
        if (rewardedAd?.isLoaded!!) {
            rewardedAd?.show(activity, object : RewardedAdCallback() {
                override fun onUserEarnedReward(p: RewardItem) {
                    UiUtils.appErrorLog(TAG, "Type: ${p.type} Amount:${p.amount}")
                }

                override fun onRewardedAdFailedToShow(p0: AdError?) {
                    super.onRewardedAdFailedToShow(p0)
                    UiUtils.appErrorLog(TAG, "onRewardedAdFailedToShow")
                }

                override fun onRewardedAdClosed() {
                    super.onRewardedAdClosed()
                    UiUtils.appErrorLog(TAG, "onRewardedAdClosed")
                }

                override fun onRewardedAdOpened() {
                    super.onRewardedAdOpened()
                    UiUtils.appErrorLog(TAG, "onRewardedAdOpened")
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }


}