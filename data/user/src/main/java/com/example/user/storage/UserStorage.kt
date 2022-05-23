package com.example.user.storage

import com.example.user.models.Token

interface UserStorage {
    fun saveState()
    fun takeState(): Boolean
    fun saveToken(token: Token)
    fun getToken(): String
}