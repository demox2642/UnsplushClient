package com.example.base_ui

import androidx.lifecycle.ViewModel
import com.example.base_ui.managers.ConnectionManager
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var connectionManager: ConnectionManager
}
