@file:OptIn(ExperimentalCoilApi::class)

package com.example.detailphoto

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.base_ui.errorlisiner.ErrorListener
import com.example.base_ui.photo.BasePhoto
import com.example.base_ui.photo.BasePhotoScreen
import com.example.base_ui.theme.AppTheme
import com.example.home.models.CategoryDomain
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
    Column() {
        Row() {
            BasePhotoScreen(unsplashImage = homePhoto, modifier = Modifier.padding(0.dp))
        }
        Row(modifier = Modifier.padding(10.dp)) {
            LocationPhoto(localText = "sdfhsdf", showLocation = {})
        }
        Row(
            modifier = Modifier.padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            PhotoCategories(photoInfo.categories)
        }
        Row(modifier = Modifier.padding(10.dp)) {
            MakePhotoInfo(exif = photoInfo.exifDomain, user = photoInfo.userDomain)
        }
    }
}

@Composable
fun LocationPhoto(localText: String, showLocation: () -> Unit) {
    Row(modifier = Modifier.padding(10.dp)) {
        Icon(Icons.Default.LocationOn, "location", tint = AppTheme.colors.systemGraphOnPrimary)
        Text(text = localText, color = AppTheme.colors.systemTextPrimary)
    }
}
@Composable
fun PhotoCategories(categoties: List<CategoryDomain>) {
    val categoryList = categoties.map {
        it.title
    }
    val categoryText = categoryList.joinToString("#")
    Row() {
        Text(text = categoryText, color = AppTheme.colors.systemTextPrimary)
    }
}

@Composable
fun MakePhotoInfo(exif: ExifDomain?, user: UserDomain?) {
    Row() {
        if (exif != null) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Made with: " + exif.make, color = AppTheme.colors.systemTextPrimary)
                Text(text = "Model: " + exif.model, color = AppTheme.colors.systemTextPrimary)
                Text(text = "Exposure: " + exif.exposureTime, color = AppTheme.colors.systemTextPrimary)
                Text(text = "Aperture: " + exif.aperture, color = AppTheme.colors.systemTextPrimary)
                Text(text = "Focal Length: " + exif.focalLength, color = AppTheme.colors.systemTextPrimary)
                Text(text = "ISO: " + exif.iso, color = AppTheme.colors.systemTextPrimary)
            }
        }
        if (user != null) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "About " + user.username + " : ", color = AppTheme.colors.systemTextPrimary)
                Text(text = user.toString(), color = AppTheme.colors.systemTextPrimary)
            }
        }
    }
}

@Preview
@Composable
fun LocationPhotoPreview() {
    LocationPhoto(localText = "sdfhsdf", showLocation = {})
}
