package com.deepspace.hab.models

/**
 * Created by abhinav on 27/09/21.
 */
data class ModuleSection(
    override var id: String? = "",
    val lessonId: String = "",
    val lessonNumber: Int = 0,
    val moduleNo: Int = 0,
    val title: String = "",
    val duration: String = "",
): BaseModel()