package com.deepspace.hab.screens.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.deepspace.common.base.BaseFragment
import com.deepspace.hab.R
import com.deepspace.hab.databinding.FragmentWelcomeBinding
import com.deepspace.hab.utils.SharedPrefManager

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>() {

    override fun getViewBinding(): FragmentWelcomeBinding =
        FragmentWelcomeBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        binding.textfieldAddName.editText?.doOnTextChanged { inputText, _, _, _ ->
            if (inputText.isNullOrEmpty()) {
                binding.tvContinueBtn.setBackgroundResource(R.drawable.bg_btn_disabled)
                binding.tvContinueBtn.isEnabled = false
            } else {
                binding.tvContinueBtn.isEnabled = true
                binding.tvContinueBtn.setBackgroundResource(R.drawable.bg_start_btn_darkgrey)
                //TODO: Save username over remote
                SharedPrefManager.getInstance(requireContext())
                    .setUserName(binding.textfieldAddName.editText?.text?.trim().toString())
            }
        }
    }

}