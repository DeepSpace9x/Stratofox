package com.deepspace.hab.screens.lesson

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.navArgs
import com.deepspace.common.base.BaseActivity
import com.deepspace.hab.databinding.ActivityLessonBinding
import timber.log.Timber

class LessonActivity : BaseActivity<ActivityLessonBinding>() {

    override fun getViewBinding(): ActivityLessonBinding =
        ActivityLessonBinding.inflate(layoutInflater)

    private val lessonViewModel: LessonViewModel by viewModels()
    private val args: LessonActivityArgs by navArgs()

    override fun create(savedInstanceState: Bundle?) {
        Timber.d("Module Details: ${args.moduleDetails}")
        lessonViewModel.moduleDetails = args.moduleDetails
    }

}