package com.echavez.labo3evaluado.Network

import com.echavez.labo3evaluado.AppConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiBody {

    private val interceptor = Interceptor { chain->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("apikey", AppConstants.ombdApiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val client = OkHttpClient()
        .newBuilder()
        .addInterceptor(interceptor)
        .build()

    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://www.omdbapi.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val API :  movieApi = retrofit().create(movieApi::class.java)

}