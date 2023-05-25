package com.zero.synefiliya.utils.di

import android.content.Context
import com.zero.synefiliya.utils.APIService
import com.zero.synefiliya.utils.Constants.Companion.BASE_URL
import com.zero.synefiliya.utils.SharedPreferenceUtil


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val TIMEOUT_IN_SECOND: Long = 15

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {


    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    fun provideUserSharedPreference(@ApplicationContext context: Context) =
        SharedPreferenceUtil(context)


    @Singleton
    @Provides
    fun provideHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(tokenInterceptor)
            .readTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides   //IN ORDER TO CREATE A RETROFIT INSTANCE IT IS DEPENDENT ON httpClient AND converterFactory SO HILT SHOULD KNOW TO CREATE THEM FIRST
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)
}