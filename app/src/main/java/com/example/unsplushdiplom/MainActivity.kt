package com.example.unsplushdiplom

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.base_ui.managers.ConnectionManager
import com.example.base_ui.theme.AppTheme.AppTheme
import com.example.base_ui.theme.appDarkColors
import com.example.base_ui.theme.appLightColors
import com.example.unsplushdiplom.ui.UnsplashMainScreen
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectionManager: ConnectionManager

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            ProvideWindowInsets {
                this.window.statusBarColor = ContextCompat.getColor(this, R.color.statusBarColor)
                val darkTheme: Boolean = isSystemInDarkTheme()
                val colors = if (darkTheme) appDarkColors() else appLightColors()
                val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }
                connectionManager.connectionLiveData.observe(this) {
                    if (!it) {
                        lifecycleScope.launch {
                            snackbarHostState.value.showSnackbar(
                                message = "No Network Connection",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                }

                AppTheme(colors = colors) {
                    Box {
                        UnsplashMainScreen()
                        // SystemUi(windows = window)
                        SnackbarHost(
                            hostState = snackbarHostState.value,
                            modifier = Modifier.align(Alignment.BottomCenter)
                        ) {
                            Snackbar(
                                modifier = Modifier.padding(12.dp),
                                backgroundColor = Color.White,
                                snackbarData = it,
                                contentColor = Color.Black
                            )
                        }
                    }
                }
            }
        }
        handleAuthCallback()
    }

    private fun handleAuthCallback() {
        val data = intent.data
        val code = data?.query?.replace("code=", "")
        Log.e("MainAct", "$code")
        code?.let { fetchToken(code) }
    }
    private fun fetchToken(code: String) {
        Log.e(TAG, "code=$code")

        viewModel.getToken(code = code)
    }
}
