package com.example.base_ui.errorlisiner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.base_ui.dialogs.CustomAlertDialog
import com.example.base_ui.dialogs.CustomErrorDialog
import com.example.base_ui.errorlisiner.models.UnsplushError
import com.example.base_ui.errorlisiner.models.UnsplushErrorType

@Composable
fun ErrorListener(
    error: UnsplushError,
    closeDialog: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {

        when (error.type) {
            UnsplushErrorType.ALERT -> {
                CustomAlertDialog(
                    title = "Alert",
                    message = error.text,
                    dismissButtonText = "Exit",
                    closeDialog = closeDialog
                )
            }
            UnsplushErrorType.WARNING -> {}
            UnsplushErrorType.CRITICAL -> {
                CustomErrorDialog(

                    title = "Uncknow Error",
                    message = error.text,
                    dismissButtonText = "Exit",
                    closeDialog = closeDialog
                )
            }
        }
    }
}
