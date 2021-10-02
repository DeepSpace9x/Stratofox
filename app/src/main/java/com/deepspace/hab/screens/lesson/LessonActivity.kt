package com.deepspace.hab.screens.lesson

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import com.deepspace.common.base.BaseActivity
import com.deepspace.domain.Resource
import com.deepspace.hab.R
import com.deepspace.hab.databinding.ActivityLessonBinding
import timber.log.Timber

class LessonActivity : BaseActivity<ActivityLessonBinding>() {

    override fun getViewBinding(): ActivityLessonBinding =
        ActivityLessonBinding.inflate(layoutInflater)

    private val lessonViewModel: LessonViewModel by viewModels()
    private val args: LessonActivityArgs by navArgs()

    override fun create(savedInstanceState: Bundle?) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        fetchModuleSections()
    }

    private fun fetchModuleSections() {
        Timber.d("Module Details: ${args.moduleDetails}")
        lessonViewModel.moduleDetails = args.moduleDetails
        lessonViewModel.fetchModuleSections(args.moduleDetails?.id!!).observe(this, {
            when (it) {
                is Resource.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                    binding.navHostFragment.visibility = View.INVISIBLE
                }
                is Resource.Success -> {
                    binding.progressbar.visibility = View.INVISIBLE
                    binding.navHostFragment.visibility = View.VISIBLE
                    lessonViewModel.moduleSectionList.postValue(it.data)
                    Timber.d("Module Sections: ${it.data}")
                }
                is Resource.Failure -> {
                    Toast.makeText(
                        this,
                        "Error while fetching module sections. Please try again later",
                        Toast.LENGTH_SHORT
                    ).show()
                    Timber.d("Fetching Module Sections failed!")
                    finish()
                }
            }
        })
    }

}