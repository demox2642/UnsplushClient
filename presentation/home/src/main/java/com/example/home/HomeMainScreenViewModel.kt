package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.home.models.HomePhoto
import com.example.home.usecase.GetPhotosListUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMainScreenViewModel @Inject constructor(
    private val getPhotosListUserCase: GetPhotosListUserCase
) : ViewModel() {
    private val _photoList = MutableStateFlow<PagingData<HomePhoto>>(PagingData.empty())
    val photoList = _photoList.asStateFlow()
    init {
        getPhotosList()
    }

    fun getPhotosList() {

        viewModelScope.launch {

            getPhotosListUserCase.getPhotosList()
                .cachedIn(viewModelScope)
                .collect {
                    _photoList.value = it
                }
        }
    }
}
