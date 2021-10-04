package com.deepspace.hab.screens.lesson

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepspace.common.base.BaseFragment
import com.deepspace.hab.databinding.FragmentLessonDetailBinding
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.noties.markwon.Markwon
import timber.log.Timber

class LessonDetailFragment : BaseFragment<FragmentLessonDetailBinding>() {

    override fun getViewBinding(): FragmentLessonDetailBinding =
        FragmentLessonDetailBinding.inflate(layoutInflater)

    private val lessonViewModel: LessonViewModel by activityViewModels()
    private val lessonArgs: LessonDetailFragmentArgs by navArgs()

    override fun onViewCreated(savedInstanceState: Bundle?) {

        val titleOfPage = "Module ${lessonArgs.moduleNo}: Lesson ${lessonArgs.lessonNo}"
        binding.tvTitleBar.text = titleOfPage

        val content = lessonViewModel.lessonContent

        val mainContent = content?.mainHeadingContent
        val subContent = content?.subHeadingContent

        binding.tvMainHeading.text = mainContent?.heading
        binding.tvMainHeadingContent.text = mainContent?.content
        if (mainContent?.imageDrawableId != null) {
            binding.ivMainHeading.visibility = View.VISIBLE
            binding.ivMainHeading.setImageResource(mainContent.imageDrawableId)
        } else {
            binding.ivMainHeading.visibility = View.GONE
        }

        val lessonDetailAdapter =
            LessonDetailAdapter()
        binding.rvSubContent.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvSubContent.adapter = lessonDetailAdapter
        lessonDetailAdapter.submitList(subContent)

    }

    data class LessonContent(
        val mainHeadingContent: Content? = null,
        val subHeadingContent: List<Content>? = null
    )

    data class Content(
        val heading: String = "",
        val content: String = "",
        val imageDrawableId: Int? = null
    )

}