package com.zahi.whereisjudang.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(): AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setupUI()
		setupDataBinding()
		sendScreenLogEvent()
	}

	override fun onDestroy() {
		if (isTaskRoot && isFinishing) {
			finishAfterTransition()
		}
		super.onDestroy()
	}

	protected abstract fun setupUI()
	protected abstract fun setupDataBinding()
	protected abstract fun sendScreenLogEvent()

	protected fun toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
		Toast.makeText(applicationContext, msg, duration).show()
	}
}
