package com.echavez.labo3evaluado.Network

import com.echavez.labo3evaluado.Entities.Movie
import com.echavez.labo3evaluado.Entities.OmbdMovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface movieApi {

    @GET("/")
    fun getMoviesByName(@Query("s") query: String): Deferred<Response<OmbdMovieResponse>>

    @GET("/")
    fun getMovieByTitle(@Query("t") query: String): Deferred<Response<Movie>>

}