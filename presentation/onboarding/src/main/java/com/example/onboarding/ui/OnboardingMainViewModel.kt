package com.example.onboarding.ui

import androidx.lifecycle.ViewModel
import com.example.presentation.onboarding.R
import com.example.user.models.OnBoarding
import com.example.user.usecase.onboarding.SaveOnboardingStateUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingMainViewModel @Inject constructor(

    private val saveOnboardingStateUserCase: SaveOnboardingStateUserCase
) : ViewModel() {

    private val _onBoardItem = MutableStateFlow<List<OnBoarding>>(emptyList())
    val onBoardItem: StateFlow<List<OnBoarding>> = _onBoardItem

    fun saveOnboardingState() {
        saveOnboardingStateUserCase.saveOnboardingState()
    }

    init {
        _onBoardItem.value = listOf(
            OnBoarding(
                R.drawable.onboarding_take_pthoto,
                R.string.onboard1
            ),
            OnBoarding(
                R.drawable.onboarding_linc_photo,
                R.string.onboard2
            ),
            OnBoarding(
                R.drawable.onboarding_download,
                R.string.onboard3
            )
        )
    }
}
