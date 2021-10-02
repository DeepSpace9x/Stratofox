package com.deepspace.hab.screens.welcome

import android.os.Bundle
import com.deepspace.common.base.BaseFragment
import com.deepspace.hab.databinding.FragmentDisclaimerBinding

class DisclaimerFragment : BaseFragment<FragmentDisclaimerBinding>() {

    override fun getViewBinding(): FragmentDisclaimerBinding =
        FragmentDisclaimerBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {

    }
}