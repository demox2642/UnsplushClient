package com.example.user.usecase.onboarding

import com.example.user.repository.UserRepository

class SaveOnboardingStateUserCase(private val userRepository: UserRepository) {

    fun saveOnboardingState() {
        userRepository.saveOnboardingState()
    }
}
