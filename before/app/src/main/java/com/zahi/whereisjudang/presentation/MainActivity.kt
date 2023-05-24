package com.zahi.whereisjudang.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zahi.whereisjudang.R
import com.zahi.whereisjudang.base.BaseActivity
import com.zahi.whereisjudang.base.ViewState
import com.zahi.whereisjudang.databinding.ActivityMainBinding
import com.zahi.whereisjudang.extensions.viewBinding
import com.zahi.whereisjudang.viewmodel.MainViewModel

class MainActivity : BaseActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: MainViewModel by viewModels()
    override fun setupUI() {
    }

    override fun setupDataBinding() {
        viewModel.run {
            state.observe(this@MainActivity) {
                when (it) {
                    is ViewState.Success<*, *> -> processSuccessBlock(it)
                    is ViewState.Failure<*, *> -> processFailureBlock(it)
                }
            }
        }
    }

    override fun sendScreenLogEvent() {}

    private fun processSuccessBlock(state: ViewState.Success<*,*>) {
        when (state.state) {

        }
    }

    private fun processFailureBlock(state: ViewState.Failure<*,*>) {
        when(state.state) {
            else -> toast(state.result ?: resources.getString(R.string.error_unknown))
        }
    }
}