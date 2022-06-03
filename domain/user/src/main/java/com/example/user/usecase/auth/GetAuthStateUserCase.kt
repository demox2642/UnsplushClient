package com.example.user.usecase.auth

import com.example.user.repository.UserRepository

class GetAuthStateUserCase(private val userRepository: UserRepository) {
    fun getAuthStateUserCase(): String = userRepository.getAuthState()
}
