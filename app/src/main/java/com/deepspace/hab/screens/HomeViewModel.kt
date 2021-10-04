package com.deepspace.hab.screens

import androidx.lifecycle.ViewModel
import com.deepspace.hab.models.Module

/**
 * Created by abhinav on 26/09/21.
 */
class HomeViewModel: ViewModel() {
    var moduleList: List<Module>? = null
}