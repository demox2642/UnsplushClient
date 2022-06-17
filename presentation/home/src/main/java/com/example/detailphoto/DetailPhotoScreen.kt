@file:OptIn(
    ExperimentalCoilApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.example.detailphoto

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.base_ui.errorlisiner.ErrorListener
import com.example.base_ui.photo.BasePhoto
import com.example.base_ui.photo.BasePhotoScreen
import com.example.base_ui.theme.AppTheme
import com.example.home.models.DetailPhoto
import com.example.home.models.ExifDomain
import com.example.home.models.UserDomain

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailPhotoScreen(photoId: String, navController: NavHostController) {

    val viewModel = hiltViewModel< DetailPhotoViewModel>()
    viewModel.getPhotoInfo(photoId = photoId)
    val photoInfo by viewModel.photoInfo.collectAsState()
    val openErrorDialog by viewModel.errorMessageState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Scaffold(
        backgroundColor = AppTheme.colors.systemBackgroundPrimary,
        content = {
            if (openErrorDialog) {
                Column() {
                    ErrorListener(
                        error = errorMessage!!,
                        closeDialog = viewModel::closeErrorDialog
                    )
                }
            } else {
                if (photoInfo != null) {
                    Log.e("DetailPhoto", "photoInfo = $photoInfo")
                    DetailPhotoContent(photoInfo!!)
                }
            }
        }
    )
}

@Composable
fun DetailPhotoContent(photoInfo: DetailPhoto) {
    val homePhoto = BasePhoto(
        id = photoInfo.id!!,
        likes = photoInfo.likes,
        urls_regular = photoInfo.urlsPhoto,
        user_name = photoInfo.userDomain?.username,
        user_fio = photoInfo.userDomain?.username,
        user_img = photoInfo.userDomain?.profileImageDomain?.small,
        likedByUser = photoInfo.likedByUser
    )
    val context = LocalContext.current

    LazyColumn(contentPadding = PaddingValues(bottom = 10.dp)) {
        stickyHeader {
            BasePhotoScreen(unsplashImage = homePhoto, modifier = Modifier.padding(0.dp))
        }
        if (photoInfo.location?.city != null) {
            item {
                Log.e("DetailPhotoContent", "location = ${photoInfo.location}")
                LocationPhoto(
                    localText = photoInfo.location?.country.toString() + " " + photoInfo.location?.city.toString(),
                    showLocation = {
                        if (photoInfo.location!!.positionDomain != null) {
                            Log.e("DetailPhotoContent", "location = ${photoInfo.location!!.positionDomain }")
                            val gmmIntentUri = Uri.parse("geo:${photoInfo.location!!.positionDomain?.latitude},${photoInfo.location!!.positionDomain?.longitude}")
                            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                            mapIntent.setPackage("com.google.android.apps.maps")
                            startActivity(context, mapIntent, null)
                        }
                    }
                )
            }
        }
        if (photoInfo.categories != null) {
            item { PhotoCategories(photoInfo.categories!!) }
        }

        item { MakePhotoInfo(exif = photoInfo.exifDomain, user = photoInfo.userDomain) }
        if (photoInfo.downloads != null) {
            item {
                DownloadImage(columnDownload = photoInfo.downloads.toString(), downLoad = {})
            }
        }
    }
}

@Composable
fun LocationPhoto(localText: String, showLocation: () -> Unit) {
    Row(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .clickable(onClick = showLocation)
    ) {
        Icon(Icons.Default.LocationOn, "location", tint = AppTheme.colors.systemGraphOnPrimary)
        Text(text = localText, color = AppTheme.colors.systemTextPrimary)
    }
}
@Composable
fun PhotoCategories(categoties: String) {

    Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)) {
        Text(text = categoties, color = AppTheme.colors.systemTextPrimary)
    }
}

@Composable
fun MakePhotoInfo(exif: ExifDomain?, user: UserDomain?) {
    Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)) {
        if (exif != null) {
            Column(modifier = Modifier.weight(1f)) {
                if (exif.make != null) {
                    Text(text = "Made with: " + exif.make, color = AppTheme.colors.systemTextPrimary)
                }
                if (exif.model != null) {
                    Text(text = "Model: " + exif.model, color = AppTheme.colors.systemTextPrimary)
                }
                if (exif.exposureTime != null) {
                    Text(text = "Exposure: " + exif.exposureTime, color = AppTheme.colors.systemTextPrimary)
                }
                if (exif.aperture != null) {
                    Text(text = "Aperture: " + exif.aperture, color = AppTheme.colors.systemTextPrimary)
                }
                if (exif.focalLength != null) {
                    Text(text = "Focal Length: " + exif.focalLength, color = AppTheme.colors.systemTextPrimary)
                }
                if (exif.iso != null) {
                    Text(text = "ISO: " + exif.iso, color = AppTheme.colors.systemTextPrimary)
                }
            }
        }
        if (user != null) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "About " + user.username + " : ", color = AppTheme.colors.systemTextPrimary)
                if (user.name != null) {
                    Text(text = user.name!!, color = AppTheme.colors.systemTextPrimary)
                }
                if (user.firstName != null) {
                    Text(text = user.firstName!!, color = AppTheme.colors.systemTextPrimary)
                }
                if (user.lastName != null) {
                    Text(text = user.lastName!!, color = AppTheme.colors.systemTextPrimary)
                }
                if (user.totalLikes != null) {
                    Text(text = user.totalLikes.toString(), color = AppTheme.colors.systemTextPrimary)
                }
                if (user.totalPhotos != null) {
                    Text(text = user.totalPhotos.toString(), color = AppTheme.colors.systemTextPrimary)
                }
                if (user.instagramUsername != null) {
                    Text(text = user.instagramUsername!!, color = AppTheme.colors.systemTextPrimary)
                }
                if (user.twitterUsername != null) {
                    Text(text = user.twitterUsername!!, color = AppTheme.colors.systemTextPrimary)
                }
            }
        }
    }
}

@Composable
fun DownloadImage(columnDownload: String, downLoad: () -> Unit) {
    Row(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxWidth()
            .clickable(onClick = downLoad),
        horizontalArrangement = Arrangement.End
    ) {
        Text(text = "Download ( $columnDownload )", color = AppTheme.colors.systemTextPrimary)
        Icon(Icons.Default.Download, "download", tint = AppTheme.colors.systemGraphOnPrimary)
    }
}

@Preview
@Composable
fun LocationPhotoPreview() {
    LocationPhoto(localText = "sdfhsdf") {}
}
