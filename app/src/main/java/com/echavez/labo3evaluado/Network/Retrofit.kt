package com.echavez.labo3evaluado.Network

import com.echavez.labo3evaluado.AppConstants
import com.echavez.labo3evaluado.Entities.Movie
import com.echavez.labo3evaluado.Entities.OmbdMovieResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Retrofit {

    @GET("/")
    fun getMoviesByName(@Query("s") query: String): Deferred<Response<OmbdMovieResponse>>

    @GET("/")
    fun getMovieByTitle(@Query("t") query: String): Deferred<Response<Movie>>

    companion object {
        private val authInterceptor = Interceptor { chain->
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

        private val ombdClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .build()

        fun retrofit() : Retrofit = Retrofit.Builder()
            .client(ombdClient)
            .baseUrl("http://www.omdbapi.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        val ombdApi : Retrofit = retrofit().create(Retrofit::class.java)
    }
}