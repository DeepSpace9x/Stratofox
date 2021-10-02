package com.deepspace.hab.screens.lesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepspace.common.base.BaseFragment
import com.deepspace.domain.Resource
import com.deepspace.domain.models.Module
import com.deepspace.domain.models.ModuleSection
import com.deepspace.hab.R
import com.deepspace.hab.databinding.FragmentLessonListBinding
import com.deepspace.hab.screens.guidelines.ResourceAdapter
import timber.log.Timber

class LessonListFragment : BaseFragment<FragmentLessonListBinding>() {

    override fun getViewBinding(): FragmentLessonListBinding =
        FragmentLessonListBinding.inflate(layoutInflater)

    private val lessonViewModel: LessonViewModel by activityViewModels()

    override fun onViewCreated(savedInstanceState: Bundle?) {
        lessonViewModel.moduleDetails?.let { setupTextualContent(it) }

        val lessonAdapter =
            LessonListAdapter { moduleSection -> goToLessonDetailActivity(moduleSection) }
        binding.rvLessonList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvLessonList.adapter = lessonAdapter

        lessonViewModel.moduleSectionList.observe(viewLifecycleOwner, {
            lessonAdapter.submitList(it)
        })
    }

    private fun setupTextualContent(module: Module) {
        val moduleName = "Module " + module.rank
        binding.tvModuleTitle.text = module.title
        binding.tvModuleDescription.text = module.description
        binding.tvModuleName.text = moduleName
        setupLineColor(module.rank)
        setupImage(module.rank)
    }

    private fun setupImage(moduleNo: Int) {
        binding.ivModuleCoverImage.setImageResource(
            when (moduleNo) {
                1 -> R.drawable.ic_planning
                2 -> R.drawable.ic_test
                3 -> R.drawable.ic_launch
                4 -> R.drawable.ic_retrieval
                else -> R.drawable.ic_planning
            }
        )
    }

    private fun setupLineColor(moduleNo: Int) {
        binding.viewLineColor.setBackgroundColor(getColorID(moduleNo))
    }

    private fun goToLessonDetailActivity(moduleSection: ModuleSection) {
        lessonViewModel.fetchLessonById(moduleSection.lessonId).observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> lessonViewModel.lessonDetail = it.data
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Unable to fetch lesson. Please try again later",
                        Toast.LENGTH_SHORT
                    ).show()
                    Timber.d("Fetching Lesson Details Failed!!")
                }
            }
        })
    }

    private fun getColorID(moduleNo: Int): Int {
        return when (moduleNo) {
            1 -> R.color.bg_module1
            2 -> R.color.bg_module2
            3 -> R.color.bg_module3
            4 -> R.color.bg_module4
            else -> R.color.bg_module1
        }
    }
}