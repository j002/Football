package com.djib.football.di

import com.djib.football.domain.reporitory.CountryRepository
import com.djib.football.network.repository.CountryRepositoryImpl
import com.djib.football.network.service.CountryService
import com.djib.football.utils.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Provides
    @Named("WEB_API")
    fun provideWebAPI(): String = Const.WEB_API

    @Provides
    fun provideRetrofit(@Named("WEB_API") webAPI: String, ): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
        return Retrofit.Builder()
            .baseUrl(webAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideCountryService(
        retrofit: Retrofit
    ): CountryService = retrofit.create(CountryService::class.java)

    @Provides
    fun provideCountryRepository(
        countryService: CountryService
    ): CountryRepository = CountryRepositoryImpl(
        countryService = countryService)

}