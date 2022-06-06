package com.example.detailphoto

import androidx.lifecycle.ViewModel
import com.example.home.usecase.GetPhotoInfoUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPhotoViewModel @Inject constructor(
    private val getPhotoInfoUserCase: GetPhotoInfoUserCase
) : ViewModel() {
    init {
        getPhotoInfoUserCase
    }
}
