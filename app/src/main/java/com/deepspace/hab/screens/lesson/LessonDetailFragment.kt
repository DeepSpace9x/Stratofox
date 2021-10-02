package com.deepspace.hab.screens.lesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deepspace.common.base.BaseFragment
import com.deepspace.hab.R
import com.deepspace.hab.databinding.FragmentLessonDetailBinding

class LessonDetailFragment : BaseFragment<FragmentLessonDetailBinding>() {

    override fun getViewBinding(): FragmentLessonDetailBinding =
        FragmentLessonDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(savedInstanceState: Bundle?) {

    }

}