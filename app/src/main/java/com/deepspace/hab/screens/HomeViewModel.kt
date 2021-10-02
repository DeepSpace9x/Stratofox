package com.deepspace.hab.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.deepspace.data.repository.remote.LessonRepoImpl
import com.deepspace.data.repository.remote.ModulesRepoImpl
import com.deepspace.data.usecases.GetAllModulesUseCase
import com.deepspace.data.usecases.GetLessonDetailUseCase
import com.deepspace.data.usecases.GetModuleSectionsUseCase
import com.deepspace.domain.Resource
import com.deepspace.domain.models.Module
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

/**
 * Created by abhinav on 26/09/21.
 */
class HomeViewModel: ViewModel() {

    private val modulesRepo = ModulesRepoImpl()
    private val getAllModulesUseCase = GetAllModulesUseCase(modulesRepo)

    var moduleList: List<Module>? = null

    val fetchModuleList = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try{
            val moduleList = getAllModulesUseCase.getAllModulesOrderBy("rank")
            emit(moduleList)
        }catch (e: Exception){
            Timber.e("Failed to fetch modules: ${e.cause}")
            emit(Resource.failed(e))
        }
    }

}