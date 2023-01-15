package com.aves.di

import com.aves.BuildConfig
import com.aves.data.network_util.AuthInterceptor
import com.aves.data.repositories.NetworkRepository
import com.aves.data.services.NetworkService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun providesAuthInterceptor() = AuthInterceptor()

    @Provides
    @Singleton
    fun providesHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofitInstance(client: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }


    @Provides
    @Singleton
    fun providesLoginService(retrofit: Retrofit): NetworkService = retrofit.create(NetworkService::class.java)


    @Provides
    @Singleton
    fun providesNetworkRepository(service: NetworkService) : NetworkRepository = NetworkRepository(service)

}