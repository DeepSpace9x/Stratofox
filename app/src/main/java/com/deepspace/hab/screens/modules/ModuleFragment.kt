package com.deepspace.hab.screens.modules

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.deepspace.common.base.BaseFragment
import com.deepspace.domain.models.Module
import com.deepspace.hab.databinding.FragmentModuleBinding
import com.deepspace.hab.screens.HomeViewModel
import timber.log.Timber

class ModuleFragment : BaseFragment<FragmentModuleBinding>() {

    override fun getViewBinding(): FragmentModuleBinding =
        FragmentModuleBinding.inflate(layoutInflater)

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(savedInstanceState: Bundle?) {
        val moduleAdapter = ModuleAdapter { module -> onModuleClicked(module) }
        binding.rvModules.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.rvModules.adapter = moduleAdapter
        moduleAdapter.submitList(viewModel.moduleList)
    }

    private fun onModuleClicked(module: Module) {
        Timber.d("Module Clicked: $module")
        findNavController().navigate(
            ModuleFragmentDirections.actionModuleFragmentToLessonActivity(module)
        )
    }

}