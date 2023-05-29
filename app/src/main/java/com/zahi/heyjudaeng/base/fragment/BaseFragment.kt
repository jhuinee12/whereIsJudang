package com.zahi.heyjudaeng.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment

abstract class BaseFragment(): Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return ComposeView(requireContext()).apply {
			setContent { SetupUI() }
		}
	}

	@Preview
	@Composable
	protected abstract fun SetupUI()

	@Composable
	fun ShowToast(message: String) {
		LaunchedEffect(true) {
			Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
		}
	}

	@Composable
	fun HeyJudaengTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
		val colors = if (darkTheme) {
			darkColors()
		} else {
			lightColors()
		}

		MaterialTheme(
			colors = colors,
			typography = MaterialTheme.typography,
			shapes = MaterialTheme.shapes,
			content = content
		)
	}

	@Composable
	fun Greeting(name: String) {
		Text(text = "Hello $name!")
	}

	private fun lightColors(): Colors {
		return androidx.compose.material.lightColors(
			primary = Color(0xFF6200EE),
			primaryVariant = Color(0xFF3700B3),
			secondary = Color(0xFF03DAC6),
			secondaryVariant = Color(0xFF018786),
			background = Color.White,
			surface = Color.White,
			onPrimary = Color.White,
			onSecondary = Color.Black,
			onBackground = Color.Black,
			onSurface = Color.Black
		)
	}

	private fun darkColors(): Colors {
		return androidx.compose.material.darkColors(
			primary = Color(0xFFBB86FC),
			primaryVariant = Color(0xFF3700B3),
			secondary = Color(0xFF03DAC6),
			background = Color(0xFF121212),
			surface = Color(0xFF121212),
			onPrimary = Color.Black,
			onSecondary = Color.Black,
			onBackground = Color.White,
			onSurface = Color.White
		)
	}
}
