package com.deepspace.common.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 * Created by abhinav on 25/09/21.
 */
abstract class VMBaseFragment<VM : ViewModel, B : ViewBinding> : BaseFragment<B>() {

    lateinit var viewModel: ViewModel

    final override fun onViewCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(getViewModelClass())
        onCreate(savedInstanceState, viewModel, binding)
    }

    protected abstract fun onCreate(
        savedInstanceState: Bundle?,
        viewModel: ViewModel,
        binding: ViewBinding
    )

    protected abstract fun getViewModelClass(): Class<VM>
}