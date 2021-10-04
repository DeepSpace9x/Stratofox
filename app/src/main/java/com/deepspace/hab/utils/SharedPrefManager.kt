package com.deepspace.hab.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * Created by abhinav on 25/09/21.
 */
class SharedPrefManager(context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SP_NAME, ACCESS_MODE)

    private fun setString(context: Context, key: String, value: String) {
        val sharedPrefManager = context.getSharedPreferences(SP_NAME, ACCESS_MODE)
        sharedPrefManager.edit {
            putString(key, value)
        }
    }

    private fun setString(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value)
        }
    }

    private fun remove(key: String) {
        sharedPreferences.edit {
            remove(key)
        }
    }

    private fun getStringOrNull(key: String): String? {
        val data = sharedPreferences.getString(key, "")
        return if (data!!.isEmpty()) null else data
    }

    private fun getString(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    private fun setInteger(key: String, value: Int) {
        sharedPreferences.edit {
            putInt(key, value)
        }
    }

    private fun getInteger(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    private fun setLong(key: String, value: Long) {
        sharedPreferences.edit {
            putLong(key, value)
        }
    }

    private fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0)
    }

    private fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
        }
    }

    private fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun setDarkModeStatus(isEnabled: Boolean) {
        setBoolean(KEY_DARK_MODE, isEnabled)
    }

    fun getDarkModeStatus(): Boolean {
        return getBoolean(KEY_DARK_MODE)
    }

    fun setUID(uid: String) {
        setString(KEY_UID, uid)
    }

    fun getUID(): String? {
        return getString(KEY_UID);
    }

    fun setUserName(userName: String) {
        setString(KEY_USERNAME, userName)
    }

    fun getUserName(): String? {
        return getStringOrNull(KEY_USERNAME);
    }

    fun setIsUserFirstTime(isFirstTime: Boolean) {
        setBoolean(KEY_FIRST_TIME_LOGIN,isFirstTime)
    }

    fun getIsUserFirstTime() : Boolean{
        return sharedPreferences.getBoolean(KEY_FIRST_TIME_LOGIN,true)
    }

    companion object {

        const val SP_NAME = "sharedPrefs"
        const val ACCESS_MODE = Context.MODE_PRIVATE
        const val KEY_DARK_MODE = "isDarkModeOn"
        const val KEY_UID = "uid"
        const val KEY_USERNAME = "username"
        const val KEY_FIRST_TIME_LOGIN = "firstTime"

        private val sharedPrefManager: SharedPrefManager? = null

        @JvmStatic
        fun getInstance(context: Context) = sharedPrefManager ?: SharedPrefManager(context)
    }

}