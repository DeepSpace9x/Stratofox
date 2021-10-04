package com.deepspace.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by abhinav on 25/09/21.
 */
abstract class BaseFragment<B: ViewBinding>: Fragment() {

    lateinit var binding: B

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(savedInstanceState)
    }

    protected abstract fun onViewCreated(savedInstanceState: Bundle?)
    protected abstract fun getViewBinding(): B
}