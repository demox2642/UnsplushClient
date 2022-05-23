package com.example.onboarding.di

//@Module
//@InstallIn(SingletonComponent::class)
//class OnboardingModule {
//    @Singleton
//    @Provides
//    fun provideUserRepository(userStorage: UserStorage): UserRepository {
//        return UserRepositoryImpl(userStorage = userStorage)
//    }
//
//    @Provides
//    fun provideSaveOnboardingStateUserCase(userRepository: UserRepository): SaveOnboardingStateUserCase {
//        return SaveOnboardingStateUserCase(userRepository = userRepository)
//    }
//}