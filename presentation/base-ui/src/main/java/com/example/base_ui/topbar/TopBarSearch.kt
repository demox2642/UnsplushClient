package com.example.base_ui.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.base_ui.R
import com.example.base_ui.theme.AppTheme

@Composable
fun TopBarSearch(
    defValueText: String,
    text: String,
    onSearchTextChanged: (String) -> Unit
) {

    var defValue by remember { mutableStateOf(defValueText) }

    Column(modifier = Modifier.fillMaxWidth().background(AppTheme.colors.systemTopBarColors)) {
        OutlinedTextField(

            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = AppTheme.colors.systemTextPrimary,
                backgroundColor = AppTheme.colors.systemBackgroundTertiary,
                cursorColor = AppTheme.colors.systemTextPrimary,
                unfocusedBorderColor = AppTheme.colors.systemTopBarColors,
                focusedBorderColor = AppTheme.colors.systemTopBarColors
            ),
            textStyle = AppTheme.typography.link,
            singleLine = true,
            label = {
                if (text.isEmpty()) {
                    Text(
                        defValue,
                        color = AppTheme.colors.systemTextPrimary,
                        style = AppTheme.typography.link
                    )
                }
            },
            maxLines = 1,
            value = text,
            onValueChange = {
                onSearchTextChanged(it)
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .onFocusEvent {
                    it.isFocused
                    if (it.isFocused) {
                        defValue = ""
                    }
                },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search), stringResource(id = R.string.search),
                    tint = AppTheme.colors.systemGraphSecondary
                )
            },
            trailingIcon = {
                ClearIcon(
                    visible = text.isNotEmpty(),
                    onClick = { onSearchTextChanged("") }
                )
            }
        )
    }
}

@Composable
fun ClearIcon(visible: Boolean, onClick: () -> Unit) {
    if (visible) IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = stringResource(id = R.string.Clear),
            tint = AppTheme.colors.systemGraphSecondary
        )
    }
}
