package com.deepspace.hab.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.deepspace.hab.models.Module
import com.deepspace.hab.screens.welcome.WelcomeActivity
import com.deepspace.hab.utils.SharedPrefManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPrefManager: SharedPrefManager
    private var isFirstLogin: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariables()
        isFirstLogin = sharedPrefManager.getIsUserFirstTime()
        if (!isFirstLogin) {
            auth.currentUser?.uid?.let {
                sharedPrefManager.setUID(it)
                Timber.d("UID from sharedPrefs: ${sharedPrefManager.getUID()}")
            }
        } else {
            performAnonymousSignin()
        }
        fetchModulesList(isFirstLogin)
    }

    private fun initVariables() {
        auth = Firebase.auth
        sharedPrefManager = SharedPrefManager.getInstance(applicationContext)
    }

    private fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    private fun fetchModulesList(isFirstLogin: Boolean) {

        val moduleList = listOf(
            Module(
                title = "Planning",
                moduleDuration = "3-4 Days",
                rank = 1,
                description = "Module 1 description"
            ),
            Module(
                title = "Test",
                moduleDuration = "5-7 Days",
                rank = 2,
                description = "Module 2 description"
            ),
            Module(
                title = "Launch",
                moduleDuration = "3-4 Days",
                rank = 3,
                description = "Module 3 description"
            ),
            Module(
                title = "Retrieval",
                moduleDuration = "5-7 Days",
                rank = 4,
                description = "Module 4 description"
            )
        )

        if (!isFirstLogin)
            shiftToHomeActivity(moduleList)
        else
            shiftToWelcomeActivity(moduleList)
    }

    private fun shiftToHomeActivity(modules: List<Module>) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putParcelableArrayListExtra(MODULE_LIST, ArrayList(modules))
        startActivity(intent)
        finish()
    }

    private fun shiftToWelcomeActivity(modules: List<Module>) {
        val welcomeIntent = Intent(this, WelcomeActivity::class.java)
        welcomeIntent.putParcelableArrayListExtra(MODULE_LIST, ArrayList(modules))
        startActivity(welcomeIntent)
        finish()
    }

    private fun performAnonymousSignin() {
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.d("Anonymous signin success")
                    fetchModulesList(isFirstLogin)
                } else {
                    Timber.w("Anonymous signin failed", task.exception)
                }
            }
    }

    companion object {
        const val MODULE_LIST = "MODULES"
    }

}