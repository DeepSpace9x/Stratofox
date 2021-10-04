package com.deepspace.hab.screens.lesson

import com.squareup.moshi.JsonClass

/**
 * Created by abhinav on 03/10/21.
 */
@JsonClass(generateAdapter = true)
data class LessonMainContent(val heading: String, val content: String, val imageId: String = "")
