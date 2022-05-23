package com.example.user.storage

import android.content.Context
import com.example.user.models.Token
import com.example.user.repository.UserRepositoryConst

class SharedPrefUserStorage(private val context: Context) : UserStorage {

    val sharedPreferences = context.getSharedPreferences(UserRepositoryConst.SHAREDPRERFERENCE, Context.MODE_PRIVATE)

    override fun saveState() {

        sharedPreferences.edit().putBoolean(UserRepositoryConst.ONBOARDING, true).apply()
    }

    override fun takeState(): Boolean {
        return sharedPreferences.getBoolean(UserRepositoryConst.ONBOARDING, false)
    }

    override fun saveToken(token: Token) {
        sharedPreferences.edit().putString(UserRepositoryConst.TOKEN, token.accessToken).apply()
    }

    override fun getToken(): String {
        return sharedPreferences.getString(UserRepositoryConst.TOKEN, "") ?: ""
    }
}
