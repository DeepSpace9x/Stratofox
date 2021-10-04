package com.deepspace.hab.screens

import android.os.Bundle
import android.webkit.WebViewClient
import com.deepspace.common.base.BaseFragment
import com.deepspace.hab.databinding.FragmentSimulationBinding


class SimulationFragment : BaseFragment<FragmentSimulationBinding>() {
    override fun onViewCreated(savedInstanceState: Bundle?) {
        binding.webview.loadUrl("https://deepspace9x.github.io/WebView/")
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.webViewClient = WebViewClient()
        binding.webview.settings.builtInZoomControls = true
    }

    override fun getViewBinding(): FragmentSimulationBinding =
        FragmentSimulationBinding.inflate(layoutInflater)


}