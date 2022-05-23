package com.example.unsplushdiplom

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.user.models.Constants.GRANT_TYPE
import com.example.user.models.Constants.SPLASH_KEY
import com.example.user.models.Constants.SPLASH_KEY_SECRET
import com.example.user.models.Constants.SPLASH_LOGIN_CALLBACK
import com.example.user.models.Constants.SPLASH_OAUTH_TOKEN_URL
import com.example.user.usecase.auth.AuthStateUserCase
import com.example.user.usecase.auth.SaveTokenUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val authStateUserCase: AuthStateUserCase,
    private val saveTokenUserCase: SaveTokenUserCase
) :
    ViewModel() {

    fun getToken(code: String) {
        viewModelScope.launch {
            try {
                saveTokenUserCase.saveToken(
                    authStateUserCase.getTokenSuspend(
                        url = SPLASH_OAUTH_TOKEN_URL,
                        clientID = SPLASH_KEY,
                        clientSecret = SPLASH_KEY_SECRET,
                        redirectURI = SPLASH_LOGIN_CALLBACK,
                        code = code,
                        grantType = GRANT_TYPE
                    )
                )
            } catch (e: Exception) {
                Log.e("MainActivityViewModel", "getToken ERROR = $e")
            }
        }
    }
}
