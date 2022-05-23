package com.example.user.usecase.auth

import com.example.user.repository.UserRepository

class GetTokenUserCase(private val userRepository: UserRepository) {

    fun getToken(): String = userRepository.getToken()
}
