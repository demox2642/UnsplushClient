package com.example.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.base_ui.managers.ConnectionManager
import com.example.home.models.HomePhoto
import com.example.home.usecase.GetPhotosListUserCase
import com.example.home.usecase.SearchPhotoUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMainScreenViewModel @Inject constructor(
    private val getPhotosListUserCase: GetPhotosListUserCase,
    private val searchPhotoUserCase: SearchPhotoUserCase
) : ViewModel() {

    @Inject
    lateinit var connectionManager: ConnectionManager

    private val _photoList = MutableStateFlow<PagingData<HomePhoto>>(PagingData.empty())
    val photoList = _photoList.asStateFlow()

    private val _refreshState = MutableStateFlow(false)
    val refreshState = _refreshState.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private val _errorMessageState = MutableStateFlow(false)
    val errorMessageState = _errorMessageState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private var currentJob: Job? = null

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
            Log.e("HomeMainVM ", "postError${_errorMessage.value.substringBefore(':') }")
            if (_errorMessage.value.substringBefore(':') != networkError) {
                _errorMessageState.value = true
            }
        }
    }

    fun closeErrorDialog() {
        _errorMessageState.value = false
    }

    fun search(searchText: String) {
        _searchText.value = searchText
        viewModelScope.launch {
            flowSearch(_searchText)
        }
    }

    suspend fun flowSearch(searchText: Flow<String>) {
        val text = _searchText.value
        _photoList.value = PagingData.empty()
        currentJob = searchText.debounce(1000)
            .mapLatest {

                if (it.length == text.length) {
                    Log.e("HomeMainVM ", "search Start it = $it, text = $text")
                    searchPhotoUserCase.searchPhotos(it)
                        .cachedIn(viewModelScope)
                        .collect {
                            it.map {
                                Log.e("HomeMainVM ", "search Start value = $it") }
                            _photoList.value = it
                        }
                }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}
