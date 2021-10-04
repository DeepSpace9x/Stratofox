package com.deepspace.hab.screens.lesson

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deepspace.hab.models.Lesson
import com.deepspace.hab.models.Module
import com.deepspace.hab.models.ModuleSection

/**
 * Created by abhinav on 02/10/21.
 */
class LessonViewModel: ViewModel() {

    var moduleDetails: Module? = null
    var moduleSectionList: MutableLiveData<List<ModuleSection>> = MutableLiveData()
    var lessonDetail: Lesson? = null

    var lessonContent: LessonDetailFragment.LessonContent? = null

}