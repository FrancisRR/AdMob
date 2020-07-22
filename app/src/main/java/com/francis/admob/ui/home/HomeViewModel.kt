package com.francis.admob.ui.home

import android.view.View
import androidx.navigation.findNavController
import com.francis.admob.R
import com.francis.admob.base.BaseViewModel

class HomeViewModel : BaseViewModel() {


    fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnbannerAd -> {
                v.findNavController().navigate(R.id.bannerAdFragment)
            }
            R.id.btInterstitialAd -> {
                v.findNavController().navigate(R.id.interstitialAdFragment)
            }
            R.id.btRewardedAd -> {
                v.findNavController().navigate(R.id.rewardedAdFragment)
            }
            R.id.btRewardedAdNewApi -> {
                v.findNavController().navigate(R.id.rewardedAdNewApiFragment)
            }
        }
    }

}