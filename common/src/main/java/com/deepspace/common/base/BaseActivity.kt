package com.deepspace.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Created by abhinav on 25/09/21.
 */
abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    lateinit var binding: B

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        create(savedInstanceState)
    }

    protected abstract fun create(savedInstanceState: Bundle?)
    protected abstract fun getViewBinding(): B
}