package com.deepspace.hab.screens.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deepspace.common.base.BaseFragment
import com.deepspace.hab.R
import com.deepspace.hab.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun getViewBinding(): FragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {


    }

}