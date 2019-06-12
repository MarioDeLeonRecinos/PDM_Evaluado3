package com.echavez.labo3evaluado.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.echavez.labo3evaluado.Dao.MovieDao
import com.echavez.labo3evaluado.Entities.Movie
import com.echavez.labo3evaluado.Entities.MoviePreview
import com.echavez.labo3evaluado.Network.movieApi

class MovieRepository (private val movieDao: MovieDao,private val api:movieApi):BaseRepository(){

    suspend fun getMoviesByName(name: String) : MutableList<MoviePreview>? {
        val moviesResponse = apiCall(
            call = { api.getMoviesByName(name).await()},
            errorMessage = "Error Fetching Movies by Name"
        )
        return moviesResponse?.Search?.toMutableList()
    }

    suspend fun getMovieByTitle(name: String) : Movie? {
        val movieResponse = apiCall(
            call = { api.getMovieByTitle(name).await()},
            errorMessage = "Error Fetching Movie by Title"
        )
        return movieResponse
    }

    @WorkerThread
    suspend fun insert(movie: Movie) = movieDao.insertMovie(movie)

    fun getAllfromMovies(): LiveData<List<Movie>> = movieDao.getAllMovies()

    fun getMovieByName(name: String) = movieDao.searchMovieByName(name)
}