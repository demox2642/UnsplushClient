package com.example.unsplushdiplom.di

import com.example.home.repository.HomeRepository
import com.example.home.usecase.GetPhotosListUserCase
import com.example.services.AuthService
import com.example.user.repository.AuthRepository
import com.example.user.repository.AuthRepositoryImpl
import com.example.user.repository.UserRepository
import com.example.user.usecase.auth.AuthStateUserCase
import com.example.user.usecase.auth.GetTokenUserCase
import com.example.user.usecase.auth.SaveTokenUserCase
import com.example.user.usecase.onboarding.GetOnboardingStateUserCase
import com.example.user.usecase.onboarding.SaveOnboardingStateUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetOnboardingStateUserCase(userRepository: UserRepository): GetOnboardingStateUserCase {
        return GetOnboardingStateUserCase(userRepository = userRepository)
    }

    @Provides
    fun provideSaveOnboardingStateUserCase(userRepository: UserRepository): SaveOnboardingStateUserCase {
        return SaveOnboardingStateUserCase(userRepository = userRepository)
    }

    @Provides
    fun provideAuthStateUserCase(authRepository: AuthRepository): AuthStateUserCase {
        return AuthStateUserCase(authRepository = authRepository)
    }

    @Provides
    fun provideGetTokenUserCase(userRepository: UserRepository): GetTokenUserCase {
        return GetTokenUserCase(userRepository = userRepository)
    }

    @Provides
    fun provideSaveTokenUserCase(userRepository: UserRepository): SaveTokenUserCase {
        return SaveTokenUserCase(userRepository = userRepository)
    }

    @Provides
    fun providesAuthInterceptor(userRepository: UserRepository): AuthInterceptor {
        return AuthInterceptor(userRepository = userRepository)
    }

    @Provides
    fun providesUserRepository(api: AuthService): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    @Provides
    fun providesGetPhotosListUserCase(homeRepository: HomeRepository): GetPhotosListUserCase {
        return GetPhotosListUserCase(homeRepository = homeRepository)
    }
}
