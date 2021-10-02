package com.deepspace.hab.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.deepspace.common.base.BaseActivity
import com.deepspace.domain.models.Module
import com.deepspace.hab.R
import com.deepspace.hab.databinding.ActivityHomeBinding
import java.util.*
import kotlin.concurrent.schedule

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun getViewBinding(): ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
    private var isSecondBackPress = false
    private val homeViewModel: HomeViewModel by viewModels()

    override fun create(savedInstanceState: Bundle?) {
        getModulesViaIntent()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation,navController)
    }

    private fun getModulesViaIntent() {
        homeViewModel.moduleList =
            intent.getParcelableArrayListExtra<Module>(SplashScreenActivity.MODULE_LIST)
    }

    override fun onBackPressed() {
        if (isSecondBackPress) {
            super.onBackPressed()
            return
        }
        isSecondBackPress = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Timer().schedule(3000) { isSecondBackPress = false }
    }
}