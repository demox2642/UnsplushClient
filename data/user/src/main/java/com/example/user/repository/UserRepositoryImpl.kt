package com.example.user.repository

import com.example.user.models.Token
import com.example.user.storage.UserStorage
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userStorage: UserStorage) : UserRepository {
    override fun getAuthState(): String = userStorage.getAuthState()

    override fun saveOnboardingState() {
        userStorage.saveAuthState("login")
    }

    override fun getOnboardingState(): Boolean = userStorage.takeState()

    override fun saveToken(token: Token) {
        userStorage.saveAuthState("main")
        userStorage.saveToken(token)
    }

    override fun getToken(): String = userStorage.getToken()
}
