package com.zahi.whereisjudang.base.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layout: Int): Fragment(layout) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupUI()
		setupDataBinding()
		sendScreenLogEvent()
	}

	protected abstract fun setupUI()
	protected abstract fun setupDataBinding()
	protected abstract fun sendScreenLogEvent()

	protected fun toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
		Toast.makeText(requireContext().applicationContext, msg, duration).show()
	}
}
