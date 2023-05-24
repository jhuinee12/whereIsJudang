package com.zahi.whereisjudang.extensions

import android.os.Build
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.zahi.whereisjudang.R

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
	crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
	bindingInflater.invoke(layoutInflater)
}

@Suppress("DEPRECATION")
fun AppCompatActivity.hideSystemUI() {
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
		window.insetsController?.let {
			it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
			window.navigationBarColor = getColor(com.zahi.domain.R.color.purple_200)
			it.hide(WindowInsets.Type.systemBars())
		}
	} else {
		window.decorView.systemUiVisibility =
			(
					// Do not let system steal touches for showing the navigation bar
					View.SYSTEM_UI_FLAG_IMMERSIVE
					// Hide the nav bar and status bar
					or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					or View.SYSTEM_UI_FLAG_FULLSCREEN
					// Keep the app content behind the bars even if user swipes them up
					or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
		window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
	}
}

@Suppress("DEPRECATION")
fun AppCompatActivity.showSystemUI() {
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
		window.setDecorFitsSystemWindows(false)
		window.insetsController?.show(WindowInsets.Type.systemBars())
	} else {
		window.decorView.systemUiVisibility = (
				View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
						or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
	}
}
