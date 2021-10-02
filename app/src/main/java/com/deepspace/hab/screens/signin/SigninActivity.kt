package com.deepspace.hab.screens.signin

import android.os.Bundle
import android.widget.Toast
import com.deepspace.common.base.BaseActivity
import com.deepspace.hab.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class SigninActivity : BaseActivity<ActivitySigninBinding>() {

    private lateinit var auth: FirebaseAuth

    override fun getViewBinding(): ActivitySigninBinding =
        ActivitySigninBinding.inflate(layoutInflater)

    override fun create(savedInstanceState: Bundle?) {
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        val currUser = auth.currentUser
        isUserLoggedIn(currUser)
    }

    private fun isUserLoggedIn(currUser: FirebaseUser?){
        if(currUser == null){
            // User not signed in perform anonymous login
            performAnonymousSignin()
        }else{
            // User signed in
            updateUI()
        }
    }

    private fun updateUI(){

    }

    private fun performAnonymousSignin(){
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Timber.d("Anonymous signin success")
                    val user = auth.currentUser
                    Timber.d("UID: ${user?.uid}")
                }else{
                    Timber.w("Anonymous signin failed",task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    isUserLoggedIn(null)
                }
            }
    }

}