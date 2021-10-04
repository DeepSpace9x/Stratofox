package com.deepspace.hab.screens.lesson

import com.squareup.moshi.JsonClass

/**
 * Created by abhinav on 03/10/21.
 */
@JsonClass(generateAdapter = true)
data class LessonSubContent(val contents: List<LessonMainContent>? = null)
