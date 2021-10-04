package com.deepspace.hab.screens.guidelines

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.deepspace.common.base.BaseFragment
import com.deepspace.hab.R
import com.deepspace.hab.databinding.FragmentGuidelinesBinding
import com.deepspace.hab.screens.HomeViewModel
import com.deepspace.hab.screens.modules.ModuleAdapter

class GuidelinesFragment : BaseFragment<FragmentGuidelinesBinding>() {

    override fun getViewBinding(): FragmentGuidelinesBinding =
        FragmentGuidelinesBinding.inflate(layoutInflater)

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(savedInstanceState: Bundle?) {
        val resourceAdapter = ResourceAdapter()
        binding.rvResources.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.rvResources.adapter = resourceAdapter
        resourceAdapter.submitList(getResourceList())

        binding.cardCommunityGuidelines.setOnClickListener {
            findNavController().navigate(GuidelinesFragmentDirections.actionGuidelinesFragmentToGuidelinesDetailActivity("community"))
        }
        binding.cardGovtProtocols.setOnClickListener {
            findNavController().navigate(GuidelinesFragmentDirections.actionGuidelinesFragmentToGuidelinesDetailActivity("govt"))
        }
    }

    private fun getResourceList(): List<Resource> {
        val resourceList = mutableListOf<Resource>()
        val bom = Resource(
            1,
            getString(R.string.tv_bill_of_materials_label),
            getString(R.string.tv_bom_subtitle),
            R.drawable.ic_bom,
            R.color.LightOrange,
            R.color.DarkGrey,
            R.drawable.bg_start_btn_darkgrey,
            R.color.White95
        )
        val inspiration = Resource(
            2,
            getString(R.string.tv_inspiration_label),
            getString(R.string.tv_inspiration_subtitle),
            R.drawable.ic_inspiration,
            R.color.Purple,
            R.color.Cream,
            R.drawable.bg_start_btn_greywhite,
            R.color.DarkGrey
        )
        resourceList.add(bom)
        resourceList.add(inspiration)
        return resourceList
    }

    data class Resource(
        val id: Int,
        val title: String,
        val subtitle: String,
        val imageId: Int,
        val bgColor: Int,
        val textColor: Int,
        val readBtnBgDrawable: Int,
        val readBtnTextColor: Int,
    )
}