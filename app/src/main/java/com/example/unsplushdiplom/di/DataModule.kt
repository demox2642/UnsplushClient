package com.example.unsplushdiplom.di

import android.content.Context
import com.example.home.repository.HomeRepository
import com.example.home.reposutory.HomeRepositoryImp
import com.example.home.reposutory.UnsplashResponseHandler
import com.example.home.services.HomeService
import com.example.services.AuthService
import com.example.services.AuthenticationInterceptor
import com.example.user.models.Constants
import com.example.user.repository.AuthRepository
import com.example.user.repository.AuthRepositoryImpl
import com.example.user.repository.UserRepository
import com.example.user.repository.UserRepositoryImpl
import com.example.user.storage.SharedPrefUserStorage
import com.example.user.storage.UserStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesOkhttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(Constants.timeOut, TimeUnit.SECONDS)
            .addNetworkInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .addInterceptor(AuthenticationInterceptor())
    }

    @Provides
    @Singleton
    fun providesRetrofitClient(
        @Named("BASE_URL") baseurl: String,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder().client(okHttpClient).baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit) = ServiceProvider(retrofit = retrofit)

    @Singleton
    @Provides
    fun provideUserStorage(@ApplicationContext context: Context): UserStorage {
        return SharedPrefUserStorage(context = context)
    }

    @Singleton
    @Provides
    fun provideUserRepository(userStorage: UserStorage): UserRepository {
        return UserRepositoryImpl(userStorage = userStorage)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(homeService: HomeService): HomeRepository {
        return HomeRepositoryImp(homeService = homeService, responseHandler = UnsplashResponseHandler())
    }

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit.Builder): AuthService {
        return retrofit.build()
            .create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeService(retrofit: Retrofit.Builder): HomeService {
        return retrofit.build()
            .create(HomeService::class.java)
    }
}
