package com.zahi.heyjudaeng.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zahi.heyjudaeng.R
import com.zahi.heyjudaeng.base.BaseActivity

class MainActivity : BaseActivity() {
    @Preview
    @Composable
    override fun SetupUI() {
        HeyJudaengTheme {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            }
        }
    }

    override fun setupViewModel() {
    }
}