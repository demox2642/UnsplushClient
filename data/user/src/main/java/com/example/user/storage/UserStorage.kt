package com.example.user.storage

import com.example.user.models.Token

interface UserStorage {
    fun saveAuthState(state: String)
    fun getAuthState(): String
    fun saveState()
    fun takeState(): Boolean
    fun saveToken(token: Token)
    fun getToken(): String
}
