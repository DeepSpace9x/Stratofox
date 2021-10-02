package com.deepspace.hab.utils

import android.content.Context
import android.content.res.Resources.NotFoundException
import android.graphics.drawable.Drawable
import timber.log.Timber

/**
 * Created by abhinav on 30/09/21.
 */
class Utils {

    companion object {
        fun isDrawableAvailable(context: Context, name: String?): Boolean {
            val resourceId = context.resources.getIdentifier(name, "drawable", context.packageName)
            return resourceId != 0
        }

        fun getDrawable(context: Context, name: String): Drawable? {
            return try {
                val resourceId =
                    context.resources.getIdentifier(name, "drawable", context.packageName)
                context.resources.getDrawable(resourceId,context.theme)
            } catch (exception: NotFoundException) {
                Timber.d("Resource not found")
                null
            }
        }

    }
}