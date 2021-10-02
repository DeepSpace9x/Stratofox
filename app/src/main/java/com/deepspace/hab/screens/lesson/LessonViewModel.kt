package com.deepspace.hab.screens.lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.deepspace.data.repository.remote.LessonRepoImpl
import com.deepspace.data.repository.remote.ModulesRepoImpl
import com.deepspace.data.usecases.GetLessonDetailUseCase
import com.deepspace.data.usecases.GetModuleSectionsUseCase
import com.deepspace.domain.Resource
import com.deepspace.domain.models.Lesson
import com.deepspace.domain.models.Module
import com.deepspace.domain.models.ModuleSection
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

/**
 * Created by abhinav on 02/10/21.
 */
class LessonViewModel: ViewModel() {

    private val modulesRepo = ModulesRepoImpl()
    private val lessonRepo = LessonRepoImpl()

    private val getModuleSectionsUseCase = GetModuleSectionsUseCase(modulesRepo)
    private val getLessonDetailUseCase = GetLessonDetailUseCase(lessonRepo)

    var moduleDetails: Module? = null
    var moduleSectionList: MutableLiveData<List<ModuleSection>> = MutableLiveData()
    var lessonDetail: Lesson? = null

    fun fetchModuleSections(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try{
            val moduleSections =getModuleSectionsUseCase.getAllModuleSections(id)
            emit(moduleSections)
        }catch (e: Exception){
            Timber.e("Failed to fetch module sections: ${e.message}")
            emit(Resource.failed(e))
        }
    }

    fun fetchLessonById(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try{
            val lesson = getLessonDetailUseCase.getLessonById(id)
            emit(lesson)
        }catch (e: Exception){
            Timber.e("Failed to fetch lesson: ${e.message}")
            emit(Resource.failed(e))
        }
    }

}