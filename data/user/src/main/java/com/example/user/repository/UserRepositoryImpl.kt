package com.example.user.repository

import com.example.user.models.Token
import com.example.user.storage.UserStorage
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userStorage: UserStorage) : UserRepository {

    override fun saveOnboardingState() {
        userStorage.saveState()
    }
    override fun getOnboardingState(): Boolean = userStorage.takeState()

    override fun saveToken(token: Token) {
        userStorage.saveToken(token)
    }

    override fun getToken(): String = userStorage.getToken()
}
