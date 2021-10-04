package com.deepspace.hab.models

import android.os.Parcelable
import com.deepspace.hab.models.BaseModel
import kotlinx.parcelize.Parcelize

/**
 * Created by abhinav on 26/09/21.
 */
@Parcelize
data class Module(
    override var id: String? = "",
    val description: String = "",
    val moduleDuration: String = "",
    val imageId: String = "",
    val noOfLessons: Int = 0,
    val rank: Int = 0,
    val title: String = "",
    val version: String = "",
): BaseModel(), Parcelable