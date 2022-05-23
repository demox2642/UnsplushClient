package com.example.onboarding.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.base_ui.dialogs.CustomErrorDialog
import com.example.presentation.onboarding.R
import java.lang.System.exit

@Preview
@Composable
fun AuthErrorScreen() {
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            CustomErrorDialog(
                title = stringResource(id = R.string.auth_error_title),
                message = stringResource(id = R.string.auth_error_message),
                dismissButtonText = stringResource(id = R.string.exit),
                closeDialog = { exit(0) }
            )
        }
    }
}
