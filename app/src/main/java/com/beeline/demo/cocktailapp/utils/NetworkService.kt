package com.beeline.demo.cocktailapp.utils

import com.beeline.demo.cocktailapp.data.api.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class NetworkService {

    private var baseurl = "https://www.thecocktaildb.com/api/json/v1/1"

    fun getApiInterface(retrofit: Retrofit = getRetrofit(), api: Api): Api {
        return retrofit.create(api::class.java)
    }

    private fun getRetrofit(okHttpClient: OkHttpClient = getOkHttpClient(getHttpLoggingInterceptor())): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient).build()
    }

    private fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }
}