package com.deepspace.hab.screens.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.deepspace.common.base.BaseFragment
import com.deepspace.hab.R
import com.deepspace.hab.databinding.FragmentWelcomeBinding
import com.deepspace.hab.utils.SharedPrefManager
import timber.log.Timber

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>() {

    override fun getViewBinding(): FragmentWelcomeBinding =
        FragmentWelcomeBinding.inflate(layoutInflater)

    private lateinit var sharedPrefs: SharedPrefManager

    override fun onViewCreated(savedInstanceState: Bundle?) {
        sharedPrefs = SharedPrefManager.getInstance(requireContext().applicationContext)
        binding.textfieldAddName.editText?.doOnTextChanged { inputText, _, _, _ ->
            if (inputText.isNullOrEmpty()) {
//                binding.tvContinueBtn.setBackgroundResource(R.drawable.bg_btn_disabled)
                binding.tvContinueBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.LightGrey))
                binding.tvContinueBtn.isEnabled = false
            } else {
                binding.tvContinueBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.DarkGrey))
                binding.tvContinueBtn.isEnabled = true
//                binding.tvContinueBtn.setBackgroundResource(R.drawable.bg_start_btn_darkgrey)
            }
        }

        binding.tvContinueBtn.setOnClickListener {
            //TODO: Save username over remote
            sharedPrefs.setIsUserFirstTime(false)
            sharedPrefs.setUserName(binding.textfieldAddName.editText?.text?.trim().toString())
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToDisclaimerFragment())
        }
    }

}