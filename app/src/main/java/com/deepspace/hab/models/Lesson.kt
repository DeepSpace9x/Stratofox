package com.deepspace.hab.models

/**
 * Created by abhinav on 26/09/21.
 */
data class Lesson(
    override var id: String? = "",
    val lessonId: String = "",
    val moduleNo: Int = 0,
    val xp: Int = 0,
    val content: Map<String,String> = HashMap(),
    val duration: String = "",
    val summary: String = "",
    val title: String = "",
): BaseModel()
