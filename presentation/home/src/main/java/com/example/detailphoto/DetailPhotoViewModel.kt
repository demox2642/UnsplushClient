package com.example.detailphoto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base_ui.errorlisiner.models.UnsplushError
import com.example.home.models.DetailPhoto
import com.example.home.usecase.GetPhotoInfoUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPhotoViewModel @Inject constructor(
    private val getPhotoInfoUserCase: GetPhotoInfoUserCase
) : ViewModel() {

    private val _photoInfo = MutableStateFlow<DetailPhoto?>(null)
    val photoInfo = _photoInfo.asStateFlow()

    private val _errorMessageState = MutableStateFlow(false)
    val errorMessageState = _errorMessageState.asStateFlow()

    private val _errorMessage = MutableStateFlow<UnsplushError?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun getPhotoInfo(photoId: String) {
        viewModelScope.launch {
            _photoInfo.value = getPhotoInfoUserCase.getPhotoInfo(photoId)
        }
    }

    fun closeErrorDialog() {
        _errorMessageState.value = false
    }
}
