package com.example.base_ui.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.base_ui.R

@Composable
fun CustomAlertDialogPermission(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    confermButtonText: String,
    dismissButtonText: String,
    closeDialog: () -> Unit,
    changePermissionState: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(15.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier
                .background(Color.White)
        ) {

            Image(
                painter = painterResource(id = R.drawable.icon_base_ui_alert),
                contentDescription = null,
                contentScale = ContentScale.Fit,
//                colorFilter  = ColorFilter.tint(
//                    color = Purple40
//                ),
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),

            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                )
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                // .background(Purple80),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                TextButton(
                    onClick = closeDialog
                ) {

                    Text(
                        dismissButtonText,
                        fontWeight = FontWeight.Bold,
                        // color = PurpleGrey40,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }

                TextButton(onClick = changePermissionState) {
                    Text(
                        confermButtonText,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CustomAlertDialog(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    dismissButtonText: String,
    closeDialog: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(15.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier
                .background(Color.White)
        ) {

            Image(
                painter = painterResource(id = R.drawable.icon_base_ui_alert),
                contentDescription = null,
                contentScale = ContentScale.Fit,
//                colorFilter  = ColorFilter.tint(
//                    color = Purple40
//                ),
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),

            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                )
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                // .background(Purple80),
                horizontalArrangement = Arrangement.Center
            ) {

                TextButton(
                    onClick = closeDialog
                ) {

                    Text(
                        dismissButtonText,
                        fontWeight = FontWeight.Bold,
                        // color = PurpleGrey40,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}
