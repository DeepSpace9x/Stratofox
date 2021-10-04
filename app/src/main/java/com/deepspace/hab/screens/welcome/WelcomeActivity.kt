package com.deepspace.hab.screens.welcome

import android.os.Bundle
import androidx.activity.viewModels
import com.deepspace.common.base.BaseActivity
import com.deepspace.hab.models.Module
import com.deepspace.hab.databinding.ActivityWelcomeBinding
import com.deepspace.hab.screens.HomeViewModel
import com.deepspace.hab.screens.SplashScreenActivity

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>() {

    override fun getViewBinding(): ActivityWelcomeBinding =
        ActivityWelcomeBinding.inflate(layoutInflater)

    private val homeViewModel: HomeViewModel by viewModels()

    override fun create(savedInstanceState: Bundle?) {
        getModulesViaIntent()
    }
    private fun getModulesViaIntent() {
        homeViewModel.moduleList =
            intent.getParcelableArrayListExtra<Module>(SplashScreenActivity.MODULE_LIST)
    }
}