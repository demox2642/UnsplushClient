package com.example.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.home.models.Order
import com.example.home.models.Photo
import com.example.home.usecase.GetPhotosListUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMainScreenViewModel @Inject constructor(
    private val getPhotosListUserCase: GetPhotosListUserCase
) : ViewModel() {
    private val _photoList = MutableStateFlow<PagingData<Photo>>(PagingData.empty())
    val photoList = _photoList.asStateFlow()
    init {
        getPhotosList()
    }

    @OptIn(InternalCoroutinesApi::class)
    fun getPhotosList() {

        viewModelScope.launch {

            getPhotosListUserCase.getPhotosList(1, 10, Order.LATEST)
                .cachedIn(viewModelScope)
                .collect {
                    _photoList.value = it
                }
        }
    }
}
