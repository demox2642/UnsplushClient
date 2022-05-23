package com.example.user.usecase.onboarding

import com.example.user.repository.UserRepository

class GetOnboardingStateUserCase(private val userRepository: UserRepository) {
    fun getOnboardingState(): Boolean = userRepository.getOnboardingState()
}
