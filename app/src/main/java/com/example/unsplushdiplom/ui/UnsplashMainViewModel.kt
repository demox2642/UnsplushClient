package com.example.unsplushdiplom.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.user.usecase.auth.GetTokenUserCase
import com.example.user.usecase.onboarding.GetOnboardingStateUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UnsplashMainViewModel @Inject constructor(
    private val getOnboardingState: GetOnboardingStateUserCase,
    private val getTokenUserCase: GetTokenUserCase

) : ViewModel() {

    private val _onboardingState = MutableStateFlow(false)
    val onboardingState = _onboardingState.asStateFlow()

    private val _haveToken = MutableStateFlow(false)
    val haveToken = _haveToken.asStateFlow()

    init {
        getOnboardingState()
        getToken()
    }

    private fun getOnboardingState() {
        val state = getOnboardingState.getOnboardingState()
        Log.e("UnsplashMainViewModel", "state=$state")
        _onboardingState.value = state
    }

    private fun getToken() {
        val token = getTokenUserCase.getToken()
        Log.e("UnsplashMainViewModel", "token=$token")
        _haveToken.value = token.isNullOrEmpty().not()
    }
}
