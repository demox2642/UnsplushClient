package com.example.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.base_ui.errorlisiner.models.UnsplushError
import com.example.base_ui.errorlisiner.models.UnsplushErrorType
import com.example.base_ui.managers.ConnectionManager
import com.example.home.models.HomePhoto
import com.example.home.usecase.GetPhotosListUserCase
import com.example.home.usecase.SearchPhotoUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.* // ktlint-disable no-wildcard-imports
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

    private val _errorMessage = MutableStateFlow<UnsplushError?>(null)
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
        Log.e("HomeMainVM ", "getPhotosList =Start")
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
        if (loadState is LoadState.Error && loadState.error.localizedMessage != null) {
            _errorMessage.value = UnsplushError(
                type = UnsplushErrorType.CRITICAL,
                text = loadState.error.localizedMessage.toString().substringBefore(':')
            )
            if (loadState.error.localizedMessage.toString().substringBefore(':') != networkError) {
                _errorMessageState.value = true
            }
        }
    }

    fun closeErrorDialog() {
        _errorMessageState.value = false
        getPhotosList()
        _searchText.value = ""
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
                    val enLang = Regex("[A-z],[0-9]+")
                    if (enLang.matches(it)) {
                        searchPhotoUserCase.searchPhotos(it)
                            .cachedIn(viewModelScope)
                            .collect {
                                _photoList.value = it
                            }
                    } else {
                        _errorMessage.value = UnsplushError(
                            type = UnsplushErrorType.ALERT,
                            text = "search is only possible in English"
                        )
                        _errorMessageState.value = true
                    }
                }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}
