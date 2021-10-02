package com.deepspace.hab.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.deepspace.domain.Resource
import com.deepspace.domain.models.Module
import com.deepspace.hab.utils.SharedPrefManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariables()
        if(isUserLoggedIn()){
            sharedPrefManager.setUID(auth.currentUser!!.uid)
            Timber.d("UID from sharedPrefs: ${sharedPrefManager.getUID()}")
        }else{
            performAnonymousSignin()
        }
        fetchModulesList()
    }

    private fun initVariables() {
        auth = Firebase.auth
        sharedPrefManager = SharedPrefManager.getInstance(this)
    }

    private fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    private fun fetchModulesList() {
        val viewModel: HomeViewModel by viewModels()
        viewModel.fetchModuleList.observe(this,{
            when(it){
                is Resource.Success -> {
                    Timber.d("Modules: ${it.data}")
                    shiftToHomeActivity(it.data)
                }
                is Resource.Failure -> {
                    Timber.d("Failed to load Modules: ${it.exception}")
                    Toast.makeText(this,"Sorry we are not able to fetch data. Please try again later",Toast.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    //TODO: Show progress bar until the content is loaded
                    Timber.d("Show Progress bar")
                }
            }
        })
    }

    private fun shiftToHomeActivity(modules: List<Module>) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putParcelableArrayListExtra(MODULE_LIST,ArrayList(modules))
        startActivity(intent)
        finish()
    }

    private fun performAnonymousSignin() {
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.d("Anonymous signin success")
                } else {
                    Timber.w("Anonymous signin failed", task.exception)
                }
            }
    }

    companion object{
        const val MODULE_LIST = "MODULES"
    }

}