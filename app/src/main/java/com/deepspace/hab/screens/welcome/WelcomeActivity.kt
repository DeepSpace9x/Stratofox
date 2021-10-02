package com.deepspace.hab.screens.welcome

import android.os.Bundle
import com.deepspace.common.base.BaseActivity
import com.deepspace.hab.databinding.ActivityWelcomeBinding

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>() {

    override fun getViewBinding(): ActivityWelcomeBinding =
        ActivityWelcomeBinding.inflate(layoutInflater)

    override fun create(savedInstanceState: Bundle?) {

    }

}