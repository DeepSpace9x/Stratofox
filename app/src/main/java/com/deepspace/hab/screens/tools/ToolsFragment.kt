package com.deepspace.hab.screens.tools

import android.os.Bundle
import com.deepspace.common.base.BaseFragment
import com.deepspace.hab.R
import com.deepspace.hab.databinding.FragmentToolsBinding
import com.google.android.material.slider.Slider

class ToolsFragment : BaseFragment<FragmentToolsBinding>() {

    override fun getViewBinding(): FragmentToolsBinding =
        FragmentToolsBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        binding.slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {

            }
            override fun onStopTrackingTouch(slider: Slider) {

            }
        })
        binding.slider.addOnChangeListener { slider, value, fromUser ->
            if(value < 20){
               binding.ivBalloon.setImageResource(R.drawable.ic_balloon_1)
            }else if(value < 40){
                binding.ivBalloon.setImageResource(R.drawable.ic_balloon_2)
            }else if(value < 60){
                binding.ivBalloon.setImageResource(R.drawable.ic_balloon_3)
            }else if(value < 75){
                binding.ivBalloon.setImageResource(R.drawable.ic_balloon_4)
            }else if(value < 85){
                binding.ivBalloon.setImageResource(R.drawable.ic_balloon_5)
            }else if(value < 95){
                binding.ivBalloon.setImageResource(R.drawable.ic_balloon_6)
            }
        }
    }

}