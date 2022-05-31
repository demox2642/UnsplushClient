package com.example.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
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

    private val _refreshState = MutableStateFlow(false)
    val refreshState = _refreshState.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private val _errorMessageState = MutableStateFlow(false)
    val errorMessageState = _errorMessageState.asStateFlow()

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

    fun changeRefreshStatus(status: Boolean) {
        _refreshState.value = status
    }

    fun postError(loadState: LoadState) {
        val networkError = "Unable to resolve host \"api.unsplash.com\""
        if (loadState is LoadState.Error) {
            _errorMessage.value = loadState.error.localizedMessage.toString()
            Log.e("Home Main VM ", "postError${_errorMessage.value.substringBefore(':') }")
            if (_errorMessage.value.substringBefore(':') != networkError) {
                _errorMessageState.value = true
            }
        }
    }

    fun closeErrorDialog() {
        _errorMessageState.value = false
    }
}
