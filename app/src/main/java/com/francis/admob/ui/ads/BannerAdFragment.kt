package com.francis.admob.ui.ads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.francis.admob.AdListener
import com.francis.admob.R
import com.francis.admob.base.BaseFragment
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.fragment_banner.*

class BannerAdFragment : BaseFragment() {

    private val TAG by lazy { BannerAdFragment::class.java.simpleName }
    private var parentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parentView = inflater.inflate(R.layout.fragment_banner, container, false)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adRequest: AdRequest = AdRequest.Builder().build()
        bannerAd1.loadAd(adRequest)
        bannerAd2.loadAd(adRequest)
        bannerAd1.adListener = AdListener()
        bannerAd2.adListener = AdListener()


    }

}