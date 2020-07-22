package com.francis.admob.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.francis.admob.R
import com.francis.admob.base.BaseFragment
import com.francis.admob.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment() {

    private val TAG by lazy { HomeFragment::class.java.simpleName }
    private var parentView: View? = null
    private var viewModel: HomeViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binder = HomeFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binder.homeViewModel = viewModel
        binder.lifecycleOwner = this
        parentView = binder.root
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}