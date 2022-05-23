package com.example.user.usecase.auth

import com.example.user.models.Token
import com.example.user.repository.UserRepository

class SaveTokenUserCase(private val userRepository: UserRepository) {
    fun saveToken(token: Token) {
        userRepository.saveToken(token)
    }
}
