package com.zahi.heyjudaeng.base.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel() {

	@Inject
	lateinit var resources: Resources

	fun handleFailure(throwable: Throwable, retryAction: () -> Unit) {
	}

	companion object {
	}
}
