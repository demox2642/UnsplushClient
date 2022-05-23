package com.example.base_ui.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.base_ui.R
import com.example.base_ui.theme.AppTheme

@Composable
fun AppTopBarNav(
    bottomText: String? = null,
    back: (() -> Unit)? = null,
    next: (() -> Unit)? = null
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.systemBackgroundPrimary)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,

    ) {
        Column() {
            if (back != null) {
                IconButton(
                    onClick = back,
                    modifier = Modifier.size(25.dp)
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowLeft,
                        tint = AppTheme.colors.systemGraphPrimary,
                        contentDescription = stringResource(id = R.string.base_ui_back)
                    )
                }
            }
        }
        Column() {
            if (bottomText != null) {
                Text(
                    text = bottomText,
                    style = AppTheme.typography.h4,
                    color = AppTheme.colors.systemTextPrimary
                )
            }
        }
        Column() {
            if (next != null) {
                IconButton(
                    onClick = next,
                    modifier = Modifier.size(25.dp)

                ) {
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        tint = AppTheme.colors.systemGraphPrimary,
                        contentDescription = stringResource(id = R.string.base_ui_next)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    Column() {
        AppTopBarNav(bottomText = "null", back = {}) {}
    }
}
